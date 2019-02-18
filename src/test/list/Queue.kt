package test.list

import test.util.LogUtil
import java.util.*

//FIFO
open class Queue<T> {
    protected val list = ArrayList<T>()

    fun push(item: T) {
        list.add(item)
    }

    fun pop(): T? = if (list.size > 0) list.removeAt(0) else null

    fun clear() {
        list.clear()
    }
}

fun main(args: Array<String>) {
    val queue = Queue<String>()
    for (i in 1..10) {
        queue.push("大俊子$i")
    }
    for (i in 0..15) {
        log(queue.pop() ?: "---")
    }
}

fun log(content: String) {
    LogUtil.log(content)
}