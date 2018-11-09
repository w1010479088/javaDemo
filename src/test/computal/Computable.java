package test.computal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}

//HashMap不是线程安全的;
// synchronized 的使用,导致多线程失效,并发性变成线性等待,吞吐量变差,可伸缩性严重不足
class Memoizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

//优点:ConcurrentHashMap是线程安全的,解决了并发行为,发挥了并发性能;
//缺陷:两个线程同时调用compute时,可能会计算相同的arg,然后保存相同的result;
// 这个出现误差的时间取决于compute的时间,如果compute时间比较长,出现误差的概率就比较大
class Memorizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

//还是存在原子性的问题:if(f == null){...}这个方法不具有原子性,导致还是会出现Memorizer2的问题;
//由于它把计算的过程放在了cache中,出现问题的概率远不如Memorizer2,漏洞已经很小很小
//Memorizer3的其他漏洞会在Memorizer4中解决
class Memorizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            });
            f = ft;
            cache.put(arg, f);
            ft.run();   // c.compute 的调用在这里
        }
        try {
            return f.get(); //等待结算结果
        } catch (ExecutionException e) {
            throw new InterruptedException(e.getMessage());
        }
    }
}

//完全解决了所有问题,perfect!
//putIfAbsent()后面的判断是重点
class Memorizer4<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                });
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException ex) {
                cache.remove(arg);
            } catch (ExecutionException ex) {
                throw new InterruptedException(ex.getMessage());
            }
        }
    }
}