package test.sync

import test.utils.LogUtil
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
/**
 *
------>pool-1-thread-1 等待中...
------>pool-1-thread-2 等待中...
------>pool-1-thread-2 开始运行...
------>pool-1-thread-2 进入B,等待...
------>pool-1-thread-1 开始运行...
------>pool-1-thread-2 退出B...
------>pool-1-thread-2 运行结束...
------>pool-1-thread-1 进入A,等待...
------>pool-1-thread-1 退出A...
------>pool-1-thread-1 运行结束...
 *
 * */
fun main(args: Array<String>) {
    val syncObject = SyncTest()
    val pool = Executors.newFixedThreadPool(2)
    val barrier = CyclicBarrier(2)
    pool.submit(SyncRun(0, barrier, syncObject))
    pool.submit(SyncRun(1, barrier, syncObject))
}

class SyncTest {

    @Synchronized
    fun getA() {
        log("进入A,等待...")
        Thread.sleep(3000)
        log("退出A...")
    }

    @Synchronized
    fun getB() {
        log("进入B,等待...")
        Thread.sleep(3000)
        log("退出B...")
    }
}

class SyncRun(private val index: Int, private val barrier: CyclicBarrier, private val sync: SyncTest) : Runnable {

    override fun run() {
        log("等待中...")
        barrier.await()
        log("开始运行...")
        when (index) {
            0 -> sync.getA()
            1 -> sync.getB()
        }
        log("运行结束...")
    }
}

private fun log(content: String) {
    val name = Thread.currentThread().name
    LogUtil.log(name + " " + content)
}