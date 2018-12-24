package test.sync

import test.util.LogUtil
import java.util.concurrent.Exchanger
import java.util.concurrent.Executors

class ExchangerTest {

    fun test() {
        val pool = Executors.newFixedThreadPool(2)
        val exchanger = Exchanger<String>()
        pool.execute(BoySay(exchanger))
        pool.execute(GirlSay(exchanger))
    }
}

class BoySay(private val exchanger: Exchanger<String>) : Runnable {
    override fun run() {
        log("男孩走出来,等待...")
        val exchanged = exchanger.exchange("我喜欢你很久了,my be-loved girl")
        log("男孩收到:$exchanged")
    }
}

class GirlSay(private val exchanger: Exchanger<String>) : Runnable {

    override fun run() {
        log("女孩子走过来...")
        val exchanged = exchanger.exchange("我也喜欢你很久了,my be-loved boy")
        log("女孩收到:$exchanged")
    }

}

private fun log(content: String) {
    LogUtil.log(content)
}

fun main(args: Array<String>) {
    ExchangerTest().test()
}