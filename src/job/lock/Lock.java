package job.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    private final java.util.concurrent.locks.Lock mLock = new ReentrantLock();
    private final Condition mCondition = mLock.newCondition();

    private void a() {
        mLock.tryLock();
        try {
            mCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    private void b() {
        mLock.lock();
        mCondition.signal();
        mLock.unlock();
    }
}
