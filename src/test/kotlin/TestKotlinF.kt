package test.kotlin

import test.util.LogUtil

//fun <T> ObjectOutputStream.findParentOfType(clazz: Class<test.kotlin.T>): T? {
//    var p = parent
//    while(p != null && !clazz.isInstance(p)){
//        p = p.parent
//    }
//    ObjectOutputStream::class::java
//    return p as T?
//}
fun main(args: Array<String>) {
//    TestKotlinF().c()
}

class TestKotlinF {

    fun a() {
        val words = "A long time ago in a galaxy far far away".split(" ")
        val shortWords = mutableListOf<String>()
        words.getShortWordsTo(shortWords, 3)
        println(shortWords)
    }

    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }
        val articles = setOf("a", "A", "an", "An", "the", "The")
        shortWords -= articles
    }

    fun b() {
        val list = mutableListOf("A", "D", "E", "F", "N")
        val set = setOf("A", "F")
        val pair: Pair<String, String> = "k1" to "v1"
        val map = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val map2 = mutableMapOf<String, String>().apply {
            this["one"] = "1"
            this["two"] = "2"
            this["three"] = "3"
        }
        val eList = emptyList<String>()
        val eSet = emptySet<String>()
        val eMap = emptyMap<String, String>()

        list -= set
        println(list)
    }

    fun c() {
        for (i in 10 downTo 1 step 3) {
            LogUtil.log("$i")
        }
    }

    class User(val name: String, val age: Int) : Comparable<User> {
        override fun compareTo(other: User): Int {
            return this.age - other.age
        }
    }

    fun d() {
        val comparator = Comparator { v1: String, v2: String -> v1.length - v2.length }
        val list = listOf("ab", "c", "abc", "abcdef")
        list.sortedWith(comparator)
        list.sortedWith(compareBy { it.length })


        val listU = listOf<User>()
        listU.sortedWith(compareBy { it.age })
        listU.maxWith(compareBy { it.age })
        listU.minWith(compareBy { it.age })
    }

    fun f() {
        val u1 = User("Jun", 18)
//        var (name, age) = u1
        val map = mapOf("a" to 1, "b" to 2)
        for ((key, value) in map) {
            LogUtil.log("$key = $value")
        }
    }

    fun g(x: Any) {
        when (x) {
            is Int -> LogUtil.log("${x + 1}")
            is String -> LogUtil.log("${x.length}")
            is IntArray -> LogUtil.log("${x.sum()}")
        }
    }

    fun h() {
        val y = null
        val x: String? = y as? String
    }

    inner class A {
        inner class B {
            fun Int.foo() {
                val a = this@A
                val b = this@B
                val c = this
                val d = this@foo
            }

            fun a() {
                val a = B()
                val b = B()
                if (a === b) {
                    //引用相等性
                }
            }
        }
    }

    data class Point(val x: Int, val y: Int)

    operator fun Point.unaryMinus() = Point(-x, -y)
}