package test.sync

import test.utils.LogUtil
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

fun main(args: Array<String>) {
    ReentrantLockTest().test()
}

class ReentrantLockTest {
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()
    private val executors = Executors.newFixedThreadPool(2)

    fun test() {
        executors.submit({
            log("First->lock")
            lock.lock()
            log("First->await")
            condition.await()
            log("First->unlock")
            lock.unlock()
        })
        Thread.sleep(1000)
        executors.submit({
            log("Second->lock")
            lock.lock()
            log("Second->signal")
            condition.signal()
            log("Second->unlock")
            lock.unlock()
        })
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}