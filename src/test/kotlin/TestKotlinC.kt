package test.kotlin

import test.util.LogUtil

fun main(args: Array<String>) {
    val a: Float = 0.01F
    val b: Float = 0.0100F
    if (a == b) {
        LogUtil.log("a == b")
    } else {
        LogUtil.log("a != b")
    }
}


class TestKotlinC {
    var user = User(mapOf("name" to "jun", "age" to 15))

    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    class MultableUser(val map: MutableMap<String, Any?>) {
        var name: String by map
        var age: Int by map

    }

    enum class LogLevel {
        DEBUG, WARN, ERROR
    }
//
//    typealias OscarsWinners = Map<String, String>
//
//    fun countJun(oscarsWinners: OscarsWinners): Int {
//        return oscarsWinners.count { it.value.contains("JUN") }
//    }

    val numRegex = "\\d+".toRegex()
    val numbers = listOf("abc", "123", "456").filter({
        return@filter it.contains("asdfas")
    })

//
//    sealed class ExprC
//
//    data class ConstC(val num: Double) : ExprC()
//
//    data class SumC(val e1: ExprC, val e2: ExprC) : ExprC()
//
//    object NotANumberC : ExprC()
//
//    fun eval(expr: ExprC): Double = when (expr) {
//        is ConstC -> expr.num
//        is SumC -> eval(expr.e1) + eval(expr.e2)
//        is NotANumberC -> Double.NaN
//    }
//
//    val e = eval(SumC(ConstC(1.0), ConstC(2.0)))

    val map = mapOf(1 to "one", 2 to "two", 3 to "three")

    fun pri(map: Map<String, String>) {
        println(map.mapValues { (_, value) ->
            "value -> $value"
        })
    }

    data class Person(val name: String, val age: Int) {
        val isAdult: Boolean get() = age >= 20
    }

    val port = "8080".toIntOrNull() ?: 80

    val items = listOf("June", "July", "August", "September", "October")

    fun forE() {
        for (index in items.indices) {
            println("item index is ${items[index]}")
        }
    }

    fun forWhen(item: Any): String = when (item) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "Unknown"
    }

    fun range() {
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            //...
        }

        if (x in 1 until y + 1) {

        }

        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.size) {
            //...
        }

        if (2 !in list.indices) {
            //...
        }

        for (x in 1..8) {
            //
        }

        for (x in 1..10 step 2) {
            //...
        }

        for (x in 9 downTo 0 step 2) {
            //...
        }

        when {
            "a" in list -> print("juicy")
            "b" in list -> print("apple fine too")
        }

        list
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }

        val notEmpty = list.filter { it.isNotBlank() }
    }

    fun defaultArgs(a: Int = 0, b: String = "") {
//    defaultArgs(b = "45")
//    defaultArgs(9)
//    defaultArgs(a = 9)
    }

    fun map(map: Map<String, String>) {
        for ((k, v) in map) {
            println("k = $k -> v = $v")
        }
    }

    class Turtle {
        fun penDown() {}
        fun penUp() {}
        fun turn() {}
        fun forward() {}
    }

    fun turtle() {
        val turtle = Turtle()
        with(turtle) {
            penDown()
            for (x in 1..5) {
                forward()
                turn()
            }
            penUp()
        }
    }

    fun swap() {
        var a = 1
        var b = 2
        a = b.also { b = a }
    }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0' until '9') {
            throw IllegalArgumentException("out of range")
        } else {
            return c.toInt() - '0'.toInt()
        }
    }

    fun whenTest() {
        outer@ for (i in 1..100) {
            //...
            inner@ for (j in 2..10) {
                //...
                break@outer
            }
        }
    }
}
