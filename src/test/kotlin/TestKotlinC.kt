package test.kotlin

class TestKotlinC {
    var user = User(mapOf("name" to "jun", "age" to 15))
}

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

typealias OscarsWinners = Map<String, String>

fun countJun(oscarsWinners: OscarsWinners): Int {
    return oscarsWinners.count { it.value.contains("JUN") }
}

val numRegex = "\\d+".toRegex()
val numbers = listOf("abc", "123", "456").filter({
    return@filter it.contains("asdfas")
})


sealed class ExprC

data class ConstC(val num: Double) : ExprC()

data class SumC(val e1: ExprC, val e2: ExprC) : ExprC()

object NotANumberC : ExprC()

fun eval(expr: ExprC): Double = when (expr) {
    is ConstC -> expr.num
    is SumC -> eval(expr.e1) + eval(expr.e2)
    is NotANumberC -> Double.NaN
}

val e = eval(SumC(ConstC(1.0), ConstC(2.0)))

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
}