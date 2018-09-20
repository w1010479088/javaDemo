package test.sync

import java.util.concurrent.CountDownLatch

class countdownlatch : Runnable {

    override fun run() {
        val latch = CountDownLatch(10)
    }
}