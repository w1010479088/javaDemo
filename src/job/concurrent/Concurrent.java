package job.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Concurrent {

    private void a() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    }

    private void b() {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
//        queue.take()
    }

    private void c() {
//        Collections.synchronizedList()
    }

    private void d() throws InterruptedException {
        Thread thread1 = new Thread();
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread();
        thread2.start();
        thread2.join();
        Thread thread3 = new Thread();
        thread3.start();
        thread3.join();
    }

    private void e() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
//        executor.scheduleAtFixedRate();
    }

    private void f(){
        ForkJoinPool pool = new ForkJoinPool();
//        pool.execute(ForkJoinTask);
//        ForkJoinTask
    }
}
