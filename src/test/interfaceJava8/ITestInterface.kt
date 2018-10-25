package test.interfaceJava8

import test.utils.LogUtil

fun main(args: Array<String>) {
    ITestInterface().say()
    ITestInterface().sayHello()
}

class ITestInterface : C {
    override fun say() {
        LogUtil.log("objectI")
    }
}

interface A {
    fun sayHello() {
        LogUtil.log("A")
    }
}

interface B {
    fun sayHello() {
        LogUtil.log("B")
    }
}

interface C : A, B {

    override fun sayHello() {
        super<B>.sayHello()
    }

    fun say()
}