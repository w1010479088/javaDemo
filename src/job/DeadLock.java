package job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.util.LogUtil;

/*
*写个简单的会产生死锁的类
* */
public class DeadLock {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public static void main(String[] args) {
        final DeadLock target = new DeadLock();
        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.execute(() -> {
            try {
                target.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executors.execute(() -> {
            try {
                target.b();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executors.shutdownNow();
        }
    }

    public void a() throws InterruptedException {
        log("a begin");
        synchronized (lockA) {
            log("a get lockA");
            Thread.sleep(1000);
            synchronized (lockB) {
                log("a get lockB");
            }
        }
    }

    public void b() throws InterruptedException {
        log("b begin");
        synchronized (lockB) {
            log("b get lockB");
            Thread.sleep(1000);
            synchronized (lockA) {
                log("b get lockA");
            }
        }
    }

    private void log(String tag) {
        LogUtil.log(tag);
    }
}
