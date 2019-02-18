package test.list

import test.util.LogUtil
import java.util.*
import kotlin.collections.ArrayList

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
    ForEachEdit().test()
}

private fun test1() {
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

class ForEachEdit {
    fun test() {
        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        list.add(6)
        for (item in list) {
            if (item == 3) {
                list.remove(item)
            }
            log(item.toString())
        }
    }
}