package test.kotlin

import java.security.Key

class TestKotlinA {

    companion object {
        fun main(args: Array<String>) {
            testIn()
        }

        private fun printTest() {
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

        private fun hasPrefix(x: Any) = when (x) {
            is String -> x.startsWith("prefix")
            else -> false
        }

        private fun test5(): Unit {
            val x = 10
            when {
                x == 5 -> println()
                x == 10 -> println()
                else -> println()
            }
        }

        private fun parseInt(str: String?): Int? {
            when {
                str!!.startsWith("dajunzi") -> return 0
                else -> return str.toIntOrNull()
            }
        }

        private fun test6(a: String?, b: String?) {
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

        private fun getStringLength(obj: Any): Int? {
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

        private fun getStringLength2(obj: Any): Int? {
            return if (obj !is String) null else obj.length
        }

        private fun getStringLength3(obj: Any): Int? = if (obj !is String) null else obj.length

        private fun getStringLength4(obj: Any): Int? = (obj as? String)?.length

        private fun test8() {
            fun printLength(obj: Any) {
                println("'$obj' string length is ${getStringLength(obj) ?: "...err, not String"}")
            }
            printLength("Incomprehensibilities")
            printLength(1000)
            printLength(listOf(Any()))
        }

        private fun test9(): Unit {
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

        private fun describe(obj: Any): String = when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a String"
            else -> "Could not describe"
        }

        private fun range() {
            val x = 10
            val y = 9
            if (x in 5..y + 2) {

            } else {

            }
        }

        private fun rang1() {
            val listNum = 1..10
            val listStr = listOf("a", "b", "c", "d")

            for (x in listNum) {
                print(x)
            }
            println()

            for (x in listNum step 2) {
                print(x)
            }
            println()

            for (x in listStr) {
                print(x)
            }
            println()

            for (x in 9 downTo 0 step 3) {
                print(x)
            }
            println()
        }

        private fun testIn() {
            val items = listOf("apple", "banana", "fruit", "akj", "auj", "ujo")
            for (x in items) {
                when (x) {
                    "orange", "hello" -> println("juicy")
                    "apple" -> println("apple is fine too")
                }
            }

            when {
                "orange" in items -> println()
                "banana" in items -> println()
            }

            items.filter { it.startsWith("a") }
                    .sortedBy { it }
                    .map { it.toUpperCase() }
                    .forEach { print(it) }
        }

        abstract class Shape(val sides: List<Double>) {

            val perimeter: Double get() = sides.sum()

            abstract fun calculateArea(): Double
        }

        interface RectangleProperties {
            val isSquare: Boolean
        }

        class Rectangle(var height: Double, var length: Double) : Shape(listOf(height, length, height, length)), RectangleProperties {

            override fun calculateArea(): Double = height * length

            override val isSquare: Boolean get() = length == height
        }

        class Triangle(var sideA: Double, var sideB: Double, var sideC: Double) : Shape(listOf(sideA, sideB, sideC)) {

            override fun calculateArea(): Double {
                val s = perimeter / 2
                return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
            }
        }

        open class A(x: Int) {
            public open val y: Int = x
        }

        open class A2(x: Int) {
            public open val y: Int = x;
        }

        interface B {
            val length: String
        }

        interface B2 {
            val length: Int
        }

        val ab: A2 = object : A2(1), B2 {
            override val length: Int = 15
        }

        fun foo() {
            val adHoc = object {
                var x: Int = 0
                var y: Int = 0
            }
        }

        lateinit var a2: A2

        sealed class Expr {

            class Const(val num: Double) : Expr()

            class Sum(val e1: Expr, val e2: Expr) : Expr()

            object NotNumber : Expr()
        }

        object Resource {
            val name = "Name"
        }

        val p: String by lazy {
            "String lazy"
        }

        fun test() {
            val result = try {

            } catch (e: ArithmeticException) {

            }
        }

        fun testLet(): Int {
            "testLet".let {
                println(it)
            }
            return 1
        }

        fun testApply() {
            ArrayList<String>().apply {
                add("testApply1")
                add("testApply2")
                clone()
                add("testApply3")
            }
        }

        fun testWith() {
            with(ArrayList<String>()) {
                add("testWith1")
                add("testWith2")
                add("testWith3")
            }.let {
                println(it)
            }
        }

        class Customer(name: String) {

            init {
                println()
            }
        }

        open class Person {
            var children: List<Person> = listOf()

            init {

            }

            constructor(parent: Person) {
                parent.children.size
            }
        }

        open class Base(p: Int)

        class Derived(p: Int) : Base(p)

    }

    class D {

    }
}