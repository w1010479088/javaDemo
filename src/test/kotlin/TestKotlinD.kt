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