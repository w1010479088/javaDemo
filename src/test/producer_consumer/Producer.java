package test.producer_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import test.util.LogUtil;

public class Producer implements Runnable {
    private static final int SLEEP_TIME = 1000;
    private static AtomicInteger mCount = new AtomicInteger();
    private Random mRandom = new Random();
    private BlockingQueue<PCData> mQueue;
    private volatile boolean mStop;

    Producer(BlockingQueue<PCData> queue) {
        this.mQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (!mStop) {
                Thread.sleep(mRandom.nextInt(SLEEP_TIME));
                PCData data = new PCData(mCount.incrementAndGet());
                mQueue.put(data);
                log("加入成功:" + data.getCount() + ",队列有:" + mQueue.size());

//                if (mQueue.offer(data, 20, TimeUnit.SECONDS)) {
//                    log("加入成功:" + data.getCount());
//                    log("共有:" + mQueue.size());
//                } else {
//                    log("加入失败:" + data.getCount());
//                }
            }
        } catch (Exception ex) {
            log(ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    void stop() {
        mStop = true;
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}
