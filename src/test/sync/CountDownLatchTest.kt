package test.sync

import test.utils.LogUtil
import java.util.concurrent.CountDownLatch

fun main(args: Array<String>) {
    Test().test()
}

class Test {
    private var count = 0

    fun test() {
        val counter = CountDownLatch(10)
        for (i in 0..10) {
            Thread(Runnable {
                if (i == 0) {
                    for (i in 0..100) {
                        count++
                        counter.countDown()
                    }
                } else {
                    counter.await()
                    log("线程启动:$i")
                }
            }).start()
        }
        counter.await()
        log("count=$count")
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}