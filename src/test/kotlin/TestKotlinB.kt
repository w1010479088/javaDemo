package test.kotlin

class TestKotlinB {
    data class User(val name: String, val age: Int)

    val jone1 = User(age = 1, name = "jone")
    val jone2 = User("name", 1)
    val olderJone = jone1.copy(age = 2)

    fun test() {
        val jane = User("Jane", 35)
        val (name, age) = jane
        println("$name, $age years old")
    }

    sealed class Expr {
        data class Const(val number: Double) : Expr()
        data class Sum(val e1: Expr, val e2: Expr) : Expr()
        object NotNumber : Expr()
    }

    fun eval(expr: Expr): Double = when (expr) {
        is Expr.Const -> expr.number
        is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
        Expr.NotNumber -> Double.NaN
    }

    class Box<T>(t: T) {
        var value = t

        fun f(a: T): T {
            return a
        }
    }

    val box = Box(1)

    interface Source<T> {
        fun next(): T
    }

    fun <T> singletonList(item: T): List<T> {
        return arrayListOf(item)
    }

    fun <T> T.basicToString(): String {
        return "dafasdfasdf"
    }

    class Outer {
        private val bar: Int = 1

        class Nested {
            fun foo() = 2
        }
    }

    val demo = Outer.Nested().foo()

    class Outer2 {
        private val bar = 1

        inner class Inner {
            fun foo() = bar
        }
    }

    val demo2 = Outer2().Inner().foo()

    abstract class Listener {
        abstract fun on1()

        abstract fun on2()
    }

    interface Listener2 {
        fun on1()

        fun on2()
    }

    val listener = object : Listener() {
        override fun on1() {
        }

        override fun on2() {
        }
    }

    val listener2 = object : Listener2 {
        override fun on1() {
        }

        override fun on2() {
        }

    }

    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    enum class ProtocolState {
        WAITING {
            override fun signal(): ProtocolState {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        },

        TALKING {
            override fun signal(): ProtocolState {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        };

        abstract fun signal(): ProtocolState
    }

    fun foo() {
        val adHoc = object {
            var x = 0
            var y = 1
        }
        val z = adHoc.x + adHoc.y
    }
}