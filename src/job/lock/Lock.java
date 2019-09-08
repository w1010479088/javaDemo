package job.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    private final java.util.concurrent.locks.Lock mLock = new ReentrantLock();
    private final Condition mCondition = mLock.newCondition();
    private final Object mLockObj = new Object();

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

    private void c() {
        synchronized (mLockObj){
            //mLockObj必须是final类型的
        }
    }
}
