package test.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import test.utils.LogUtil;

public class Test {

    public static void main(String[] args) {
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(1);
        ExecutorService service = Executors.newCachedThreadPool();

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

        try {
            Thread.sleep(10 * 1000);
            p1.stop();
            p2.stop();
            p3.stop();
            Thread.sleep(3 * 1000);
            service.shutdown();
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}
