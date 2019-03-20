package test.condition

import java.util.concurrent.locks.ReentrantLock

class ConditionTest {

    fun start() {
        val lock = ReentrantLock()
        val condition = lock.newCondition()
        val condition1 = lock.newCondition()
        condition.await()
        condition.signal()
        condition1.await()
        condition1.signal()
    }
}