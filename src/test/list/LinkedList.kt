package test.list

class LinkedList<T> : Queue<T>(), ILinkable<T> {

    override fun previous(item: T): T? {
        val index = list.indexOf(item)
        return if (index > 0) list[index - 1] else null
    }

    override fun next(item: T): T? {
        val index = list.indexOf(item)
        return if (index > 0 && index < list.size - 2) list[index + 1] else null
    }
}

interface ILinkable<T> {

    fun previous(item: T): T?

    fun next(item: T): T?
}

fun main(args: Array<String>) {
    val list = LinkedList<String>()
    for (i in 1..10) {
        list.push("大俊子$i")
    }

    log(list.previous("大俊子5") ?: "-----")
    log(list.next("大俊子5") ?: "-----")
}