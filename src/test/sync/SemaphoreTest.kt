package test.sync

import test.util.LogUtil
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore

class SemaphoreTest {

    fun test() {
        val semaphore = Semaphore(3, true)
        val executor = Executors.newFixedThreadPool(30)
        for (i in 1..100) {
            executor.submit({
                log("查看情况:$i,${if (semaphore.availablePermits() > 0) "可以执行" else "等待..."}")
                semaphore.acquire()
                Thread.sleep(100)
                log("执行任务:$i,剩余空位:${semaphore.availablePermits()}")
                semaphore.release()
                log("完成任务:$i")
            })
        }
    }

    fun test2() {
        val thread = Thread()
        val pool = Executors.newFixedThreadPool(10)
        pool.submit(thread)
    }
}

fun main(args: Array<String>) {
    SemaphoreTest().test()
}

private fun log(content: String) {
    LogUtil.log(content)
}