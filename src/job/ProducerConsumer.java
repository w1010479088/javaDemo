package job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import test.util.LogUtil;

class Test {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer("A", queue);
        Consumer consumer1 = new Consumer("B", queue);
        Consumer consumer2 = new Consumer("C", queue);
        ExecutorService worker = Executors.newCachedThreadPool();
        worker.execute(producer);
        worker.execute(consumer1);
        worker.execute(consumer2);
        try {
            Thread.sleep(20 * 1000);
            worker.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    private final BlockingQueue<String> mQueue;
    private final String mName;
    private int mIndex;

    Producer(String name, BlockingQueue<String> queue) {
        this.mName = name;
        this.mQueue = queue;
        LogUtil.log(mName + " created");
    }

    @Override
    public void run() {
        while (true) {
            try {
                mIndex++;
                LogUtil.log(mName + " Produced = " + mIndex);
                mQueue.put(String.valueOf(mIndex));
                Thread.sleep(2 * 1000);
            } catch (Exception ex) {
                LogUtil.log("Error = " + ex.getMessage());
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<String> mQueue;
    private final String mName;

    Consumer(String name, BlockingQueue<String> queue) {
        this.mName = name;
        this.mQueue = queue;
        LogUtil.log(mName + " created");
    }

    @Override
    public void run() {
        while (true) {
            try {
                String product = mQueue.take();
                LogUtil.log(mName + " Consumed = " + product);
            } catch (Exception ex) {
                LogUtil.log("Error = " + ex.getMessage());
                break;
            }
        }
    }
}
