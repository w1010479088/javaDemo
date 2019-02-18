package test.design.responbility

import test.util.LogUtil

fun main(args: Array<String>) {
    val task = Task(150)
    val handler1 = Handler1("1号")
    val handler2 = Handler2("2号")
    val handler3 = Handler3("3号")

    handler1.nextHandler(handler2)
    handler2.nextHandler(handler3)

    handler1.execute(task)
}

data class Task(val price: Int)

abstract class BaseHandler(val name: String) {
    private var next: BaseHandler? = null

    fun nextHandler(next: BaseHandler) {
        this.next = next
    }

    fun execute(task: Task) {
        if (needHandle(task)) {
            log("$name 处理了订单:${task.price}")
        } else {
            next?.execute(task)
        }
    }

    abstract fun needHandle(task: Task): Boolean
}

class Handler1(name: String) : BaseHandler(name) {

    override fun needHandle(task: Task) = task.price < 100
}

class Handler2(name: String) : BaseHandler(name) {

    override fun needHandle(task: Task) = task.price < 1000
}

class Handler3(name: String) : BaseHandler(name) {

    override fun needHandle(task: Task) = task.price < 10000
}

private fun log(content: String) {
    LogUtil.log(content)
}