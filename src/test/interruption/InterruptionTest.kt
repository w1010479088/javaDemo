package test.interruption

import test.util.LogUtil
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    InterruptionTest().start()
}

class InterruptionTest {

    fun start() {
        val service: ExecutorService = Executors.newSingleThreadExecutor()
        val future = service.submit(CallableSub())
        val result = future.get(1, TimeUnit.SECONDS)
        log(result)
    }
}

private class CallableSub : Callable<String> {

    override fun call(): String {
        log("Callable Run")
//        Thread.sleep(10 * 1000)
        return "CallableSub Return"
    }

}

private fun log(content: String) {
    LogUtil.log(content)
}