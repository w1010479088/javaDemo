package test.kotlin

import test.util.LogUtil


fun main(args: Array<String>) {
    Sub21("Jun", 1)
}

class Invoice {}
class Empty
class PersonD constructor(name: String) {}
class PersonD2(name: String) {}
class PersonD3 private constructor(name: String) {}
class InitOrderDemo(name: String) {
    val param1 = "First param: $name".also(::println)

    init {
        println("First init")
    }

    val param2 = "Second param : $name".also(::println)

    init {
        println("Second init")
    }
}

class PersonD4(val name: String) {
    constructor(name: String, age: Int) : this(name) {
        LogUtil.log("Second")
    }

    constructor(name: String, age: Int, heigth: Double) : this(name, age) {
        LogUtil.log("Third")
    }
}

class Constructors {
    init {
        LogUtil.log("initA")
    }

    constructor(i: Int) {
        LogUtil.log("Constructor")
    }

    init {
        LogUtil.log("initB")
    }
}

open class Base(p: Int) {
    open val x: Int get() = 1

    open fun v() {

    }

    fun nv() {

    }
}

open class Sub(p: Int) : Base(p) {
    override val x: Int = 1

    final override fun v() {
        super.v()
    }
}

class Sub2(p: Int) : Sub(p) {

}

open class Base2(val name: String) {
    open val size: Int = name.length.also { LogUtil.log("name size in base $it") }

    init {
        LogUtil.log("Base2 init")
    }

    open fun f() {

    }

}

class Sub21(name: String, val age: Int) : Base2(name) {
    override val size: Int = (super.size + age).also {
        LogUtil.log("name size in sub21 $it")
    }

    init {
        LogUtil.log("Sub21 init")
    }

    override fun f() {
        super.f()
    }

    inner class Sub3 {
        fun g() {
            super@Sub21.f()
            LogUtil.log("${super@Sub21.size}")
        }
    }
}

open class A {
    open fun f() {

    }

    fun a() {

    }

    var isEmpty: Boolean = true
        get() = this is Any
        private set(value) {
            field = value
        }
}

interface B {
    fun f() {}
    fun b() {}
}

class C : A(), B {

    override fun f() {
        super<A>.f()
        super<B>.f()
    }

    private var _table: Map<String, Int>? = null

    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap()
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}

const val TYPE = "TYPE"

object Type {
    const val TYPE2 = "TYPE2"
}

class Type2 {
//    const val TYPE = "TYPE3"

    companion object {
        const val TYPE3 = "TYPE3"
    }
}

public class MyTest {
    lateinit var subject: Any

    fun f() {

    }
}

internal class Obj

open class Outer {
    public val d = 4
    protected open val b = 2
    internal val c = 3
    private val a = 1

    protected class Nested {
        public val e: Int = 5
    }
}

class Innter : Outer() {
    override val b: Int
        get() = 5
}

class Unrelated(outer: Outer)

fun Unrelated.swap(): Int {
    return 0
}

fun fx() {
    Unrelated(Outer()).swap()
}

open class CC

class D : CC()

fun CC.f() = "C.f"

fun D.f() = "D.f"

fun pF(c: CC) {
    println(c.f())
}

class CD {
    fun f() {
        println("CD - f()")
    }
}

fun CD.f() {
    println("CD -f() extension")
}

open class K {
    fun f() {

    }
}

class T {
    fun K.f() {
        toString()
        this@T.toString()
    }
}

data class User2(val name: String, val age: Int) {
    var height: Int = 0
}

fun f() {
    val (name, age) = User2("Jun", 12)
    println("$name , $age")
}


sealed class Expr

data class Const(val num: Double) : Expr()

data class Sum(val v1: Expr, val v2: Expr) : Expr()

object NotANumber : Expr()

class Outer1 {
    fun f() {

    }

    class Inner1 {
        //...
    }

    inner class Inner2 {
        //..
        fun f() {

        }

        fun f1() {
            this@Outer1.f()
            this@Inner2.f()
        }
    }
}

interface K2 {
    fun f1()
    fun f2()
}

fun f2() {
    Outer1.Inner1()
    Outer1().Inner2()
    f3(object : K2 {
        override fun f1() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun f2() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    })
}

fun f3(k: K2) {

}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(var rgb: Int) {
    RED(0xFF0000) {
        override fun color(): Int {
            return 0xFF0000
        }

    },

    GREEN(0x00FF00) {
        override fun color(): Int {
            return 0x00FF00
        }

    },

    BLUE(0x0000FF) {
        override fun color(): Int {
            return 0x0000FF
        }

    };

    abstract fun color(): Int
}

