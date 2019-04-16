package test.interview

import test.util.LogUtil
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    CollectionTest().swap()
}

class CollectionTest {
    fun iterator() {
        val list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")


        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            LogUtil.log(next)
        }

        val iterator2 = list.listIterator(list.size)
        while (iterator2.hasPrevious()) {
            val index = iterator2.previousIndex()
            val previous = iterator2.previous()
            LogUtil.log("$index+$previous")
        }
    }

    fun link() {
        val list = LinkedList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("7")

        list.addFirst("1-1")
        list.addLast("7-1")
        val first = list.removeFirst()
        val last = list.removeLast()
    }

    fun weak() {
        val weak = WeakHashMap<String, String>()

    }

    fun swap() {
        var txt = "hello this is computer"
        val items = txt.split(" ")
        val newString = StringBuilder()
        val iterator = items.listIterator(items.size)
        while (iterator.hasPrevious()) {
            newString.append(iterator.previous() + " ")
        }
        txt = newString.toString()
        LogUtil.log(txt)
    }
}