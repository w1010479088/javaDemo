package test.producer_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import test.utils.LogUtil;

public class Consumer implements Runnable {
    private static final int SLEEP_TIME = 1000;
    private BlockingQueue<PCData> mQueue;
    private Random mRandom = new Random();

    Consumer(BlockingQueue<PCData> queue) {
        this.mQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                PCData data = mQueue.take();
                log("消费了:" + data.getCount());
                Thread.sleep(mRandom.nextInt(SLEEP_TIME));
            }
        } catch (Exception ex) {
            log(ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}
