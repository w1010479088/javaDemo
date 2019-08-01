package test.kotlin

import test.util.LogUtil
import java.util.Arrays.asList
import kotlin.properties.Delegates

fun main(args: Array<String>) {
//    val m = OOM()
//    LogUtil.log(m.a)
//    LogUtil.log(m.a)

//    val user = UserX()
//    user.name = "a"
//    user.name = "b"
    fooB(5, "a", "b", "c", "d")
}

object SingleTon {
    var param: Int = 0
    fun f1() {

    }

    fun f2() {

    }
}

fun fe() {
    SingleTon.f1()
    SingleTon.param = 2
    var obj = MyClass.Named.getInstance()
    var n = ANR.create()
}

class MyClass {

    companion object Named {

        fun getInstance(): MyClass = MyClass()
    }
}

interface Factory<T> {
    fun create(): T
}

class ANR {
    companion object : Factory<ANR> {

        @JvmStatic override fun create(): ANR {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

class OOM {
    val a: String by lazy {
        LogUtil.log("get mounted")
        "Hello"
    }
}

class UserX {
    var name: String by Delegates.observable("init") { _, old, new ->
        run {
            LogUtil.log("$old -> $new")
        }
    }

}

class UserN(map: MutableMap<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun testUserN() {
    val user = User(mapOf(
            "name" to "Jun",
            "age" to 10
    ))
}

fun fooB(a: Int, vararg params: String) {
    LogUtil.log("$a")
    for (item in params) {
        LogUtil.log(item)
    }
}

fun <T> asList(vararg items: T): List<T> {
    val result = ArrayList<T>()
    for (item in items) {
        result.add(item)
    }
    return result
}

fun testList() {
    val a = arrayOf("a", "b", "c")
    val list = asList("a", "b", *a, "c")
}