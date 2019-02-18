package test.thread_local;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import test.util.LogUtil;

public class Test {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Object obj = new Object();
        ThreadLocal<Object> objLocal = new ThreadLocal<>();
        objLocal.set(obj);
        for (int i = 0; i < 3; i++) {
            executors.execute(new Runer(objLocal));
        }
//        executors.shutdown();
    }

    private static class Runer implements Runnable {
        private ThreadLocal<Object> objLocal;

        Runer(ThreadLocal<Object> objLocal) {
            this.objLocal = objLocal;
        }

        @Override
        public void run() {
            log(String.valueOf(objLocal.get().hashCode()));
        }
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}


class TestConcurrent {

    void arrayList() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

//        SortedMap sortedMap = new SortedMap();
//        SortedSet sortedSet = new SortedSet();

//        CompletionService.class
//        ThreadPoolExecutor
        Runtime.getRuntime().availableProcessors();
//        AtomicReference
//        ConcurrentSkipListMap;
//        ConcurrentSkipListSet
        Thread.currentThread().interrupt();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadScheduledExecutor();
        Executors.newScheduledThreadPool(2);
        Executors.newWorkStealingPool();
        Class classCallable = Callable.class;
        Class classFuture = Future.class;
//        ReentrantLock.class;
        new ReentrantLock();
    }

    private void fork() {
//        new ForkJoinPool(2);
//        new ForkJoinTask<TestConcurrent>();
//        new ForkJoinWorkerThread()
    }

    private void notify1() throws Exception {
        Object object = new Object();
        try {
            object.wait();
            object.notify();
        } catch (InterruptedException ex) {
            throw new Exception(ex.getCause());
        }
        Thread.holdsLock(object);
    }

    private void reentrantLock() {
        try {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            lock.unlock();
            lock.getHoldCount();
            lock.getQueueLength();
            lock.isFair();
            lock.lockInterruptibly();
            lock.tryLock(20, TimeUnit.SECONDS);
            lock.isHeldByCurrentThread();
        } catch (InterruptedException ex) {
            //
        }
    }

    private void swing() {
    }

    private void concurrentList() {
//        ConcurrentLinkedQueue;
        SingleTon.INSTANCE.get();
    }

    private void animator(){
        ValueAnimator animator = new ValueAnimator();
        animator.addUpdateListener(animation -> {

        });
        animator.cancel();
        animator.end();

        ObjectAnimator animator1 = new ObjectAnimator();
        animator1.setTarget(this);
        animator1.setIntValues(1,2,5);

    }
}

enum SingleTon {

    INSTANCE(4, "4");

    private int key;
    private String value;

    public int key() {
        return key;
    }

    public String value() {
        return value;
    }

    public String get() {
        return value;
    }

    SingleTon(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

class SingleTon2 {
    private SingleTon2() {
    }

    private static class Holder {
        private static final SingleTon2 INSTANCE = new SingleTon2();
    }

    public static SingleTon2 getInstance() {
        return Holder.INSTANCE;
    }
}