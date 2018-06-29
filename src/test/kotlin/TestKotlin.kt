package test.kotlin

import java.security.Key

class TestKotlin {

    fun main(args: Array<String>) {
        println("大俊子在此!" + sum(5, 3))
        println("大俊子来了!${sum2(10, 20)}")
        println("大俊子又来了!${divide(3.2f, 5)}")
    }

    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    private fun sum2(a: Int, b: Int) = a + b

    private fun divide(a: Float, b: Int) = a / b

    private fun testVal(): Unit {
        val a: Int = 1
        val b = 2
        val c: Int
        c = 3
        println("a = $a, b=$b, c=$c")
    }

    private fun testVar(): Unit {
        var a = 5
        val b = 6
        a++
//        b++
        println("a = $a, b = $b")
    }

    val PI = 3.14
    var x = 0

    private fun incrementX(): Unit {
        x++
    }

    private fun test1() {
        var a = 1
        val s1 = "a is $a"
        a = 2
        val s2 = "${s1.replace("is", "was")}, but now is $a"
    }

    private fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    private fun maxOf2(a: Int, b: Int) = if (a > b) a else b

    private fun test2() {
        println("max of 0 and 42 is ${maxOf(0, 42)}")
    }

    private fun test3(): Unit {
        val a = 3
        val b = 5
        var max = if (a > b) a else b
        var max2 = if (a > b) {
            println("a > b")
            a
        } else {
            println("a < b")
            b
        }
    }

    private fun parseInt() = 3

    private fun test4(): Unit {
        val x = 2
        val y: Int
        y = when (x) {
            1 -> {
                println("x=1")
                1
            }
            2 -> {
                println("x=2")
                2
            }
            3, 4, 5 -> {
                println("x=3,4,5")
                6
            }
            9 -> {
                println("x=9")
                9
            }
            else -> {
                println("x is neither 1 nor 2")
                -1
            }
        }
        println("y=$y")

        val z = 10

        when (z) {
            parseInt() -> println("3 defined for z")
            else -> println("not 3 defined for z")
        }

        when (x) {
            in 1..10 -> println()
            in 10..20 -> println()
            !in 30..60 -> println()
            else -> println()
        }
    }

    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        is TestKotlin -> x.maxOf(10, 20) > 10
        else -> false
    }

    fun test5(): Unit {
        val x = 10
        when {
            x == 5 -> println()
            x == 10 -> println()
            else -> println()
        }
    }

    fun parseInt(str: String?): Int? {
        when {
            str!!.startsWith("dajunzi") -> return 0
            else -> return str.toIntOrNull()
        }
    }

    fun test6(a: String?, b: String?) {
        val x = parseInt(a)
        val y = parseInt(b)
        if (x != null && y != null) {
            println()
        } else {
            println()
        }
        when {
            (x != null && y != null) -> println()
            else -> println("either ")
        }
    }

    fun getStringLength(obj: Any): Int? {
        return when (obj) {
            is String -> {
                obj.length
            }
            is OutOfMemoryError -> {
                obj.printStackTrace()
                2
            }
            is Key -> {
                obj.format
                2
            }
            else -> {
                obj.hashCode()
                null
            }
        }
    }

    fun getStringLength2(obj: Any): Int? {
        return if (obj !is String) null else obj.length
    }

    fun getStringLength3(obj: Any): Int? = if (obj !is String) null else obj.length

    fun getStringLength4(obj: Any): Int? = (obj as? String)?.length

    fun test8() {
        fun printLength(obj: Any) {
            println("'$obj' string length is ${getStringLength(obj) ?: "...err, not String"}")
        }
        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))
    }

    fun test9(): Unit {
        val items = listOf("apple", "banana", "watermelon")

        for (item in items) {
            println(item)
        }

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    fun describe(obj: Any): String = when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "Could not describe"
    }

    fun rang() {
        val x = 10
        val y = 9
        if (x in 5..y + 2) {

        } else {

        }
    }
}