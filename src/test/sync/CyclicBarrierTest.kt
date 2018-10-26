package test.sync

import test.utils.LogUtil
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    CyclicBarrierTest().test()
}

class CyclicBarrierTest {

    fun test() {
        val barrier = CyclicBarrier(10, Runnable {
            log("开始!")
        })
        val executor = Executors.newFixedThreadPool(10)
        for (i in 1..10) {
            executor.submit(Runner("大俊子$i", barrier))
        }
    }
}

class Runner(val name: String, val barrier: CyclicBarrier) : Runnable {

    override fun run() {
        log("$name 准备...")
        barrier.await()
        log("$name 开始跑! ${System.currentTimeMillis()}")
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}