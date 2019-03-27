package test.sync

import test.util.LogUtil
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    YieldTest().test()
}

class YieldTest {

    fun test() {
        val latch = CountDownLatch(100)
        val service = Executors.newCachedThreadPool()
        val barrier = CyclicBarrier(2)
        service.execute(FirstRun(latch, barrier))
        service.execute(SecondRun(latch, barrier))
        latch.await()
        service.shutdownNow()
    }
}

class FirstRun(private val latch: CountDownLatch, private val barrier: CyclicBarrier) : Runnable {

    override fun run() {
        barrier.await()
        while (!Thread.currentThread().isInterrupted) {
            LogUtil.log("FirstRun------------->")
            latch.countDown()
            Thread.yield()
        }
    }
}

class SecondRun(private val latch: CountDownLatch, private val barrier: CyclicBarrier) : Runnable {

    override fun run() {
        barrier.await()
        while (!Thread.currentThread().isInterrupted) {
            LogUtil.log("SecondRun--->")
            latch.countDown()
            Thread.yield()
        }
    }
}