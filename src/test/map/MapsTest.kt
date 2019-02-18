package test.map

import test.util.LogUtil
import java.util.concurrent.ConcurrentHashMap

class MapsTest {

    fun test() {
        val hashMap = HashMap<String, String>()
        val map = ConcurrentHashMap<String, String>()
        map.put("1", "1-daf")
        map.put("2", "2-daf")
        map.put("3", "3-daf")
        map.put("4", "4-daf")
        map.put("5", "5-daf")
        map.put("6", "6-daf")
        map.put("7", "7-daf")
        map.put("8", "8-daf")
        map.put("9", "9-daf")
        val entries = map.entries
        val keys = map.keys
        val values = map.values
        LogUtil.log(entries.toString())
        LogUtil.log(keys.toString())
        LogUtil.log(values.toString())
    }
}

fun main(args: Array<String>) {
    //    MapsTest().test()
    LogUtil.log(tableSizeFor(3).toString())
}

const val MAXIMUM_CAPACITY = 1 shl 30
internal fun tableSizeFor(cap: Int): Int {
    var n = cap - 1
    n = n or n.ushr(1)
    n = n or n.ushr(2)
    n = n or n.ushr(4)
    n = n or n.ushr(8)
    n = n or n.ushr(16)
    return if (n < 0) 1 else if (n >= MAXIMUM_CAPACITY) MAXIMUM_CAPACITY else n + 1
}