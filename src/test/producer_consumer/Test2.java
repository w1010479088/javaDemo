package test.producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.util.LogUtil;

public class Test2 {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);
        Producer2 producer = new Producer2(queue);
        Consumer2 consumer1 = new Consumer2("1号--->", queue);
        Consumer2 consumer2 = new Consumer2("2号--->", queue);

        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(producer);
        pool.execute(consumer1);
        pool.execute(consumer2);
    }
}

class Producer2 implements Runnable {
    private final BlockingQueue<String> queue;
    private Random mRandom = new Random();

    Producer2(BlockingQueue<String> queue) {
        if (queue == null) {
            throw new RuntimeException("queue is null,please check!");
        }
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                } else {
                    String product = String.valueOf(mRandom.nextInt(10000));
                    queue.put(product);
                    LogUtil.log(product);
                    Thread.sleep(1 * 1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer2 implements Runnable {
    private final BlockingQueue<String> queue;
    private final String tag;

    Consumer2(String tag, BlockingQueue<String> queue) {
        if (queue == null) {
            throw new RuntimeException("queue is null,please check!");
        }
        this.tag = tag;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                } else {
                    Thread.sleep(3 * 1000);
                    LogUtil.log(tag + queue.take());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
