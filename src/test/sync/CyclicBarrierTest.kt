package test.sync

import test.utils.LogUtil
import java.util.concurrent.Callable
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
            executor.execute(Runner("大俊子$i", barrier))
        }
        val future = executor.submit(CallRunner())
        val getBoolean = future.get()
        if (getBoolean) {

        } else {

        }
        val hashMap = HashMap<String, String>()
        hashMap.put("大俊子", "在此!")
    }
}

class Runner(val name: String, val barrier: CyclicBarrier) : Runnable {

    override fun run() {
        log("$name 准备...")
        barrier.await()
        log("$name 开始跑! ${System.currentTimeMillis()}")
    }
}

class CallRunner : Callable<Boolean> {

    override fun call(): Boolean {
        log("call return")
        return false
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}