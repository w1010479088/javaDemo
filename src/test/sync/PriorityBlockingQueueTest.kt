package test.sync

import test.util.LogUtil
import java.util.concurrent.PriorityBlockingQueue

fun main(args: Array<String>) {
    PriorityBlockingQueueTest().test()
}

class PriorityBlockingQueueTest {
    fun test() {
        val blockingQueue = PriorityBlockingQueue<Runnable>(3)
        blockingQueue.put(Runnable {  })
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}