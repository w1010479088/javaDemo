package interview

import test.util.LogUtil

class Test37 {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val pos = 37
            val num = Test37().num(pos - 1)
            System.out.print(num)
        }
    }

    fun num(pos: Int): Int {
        var preFirst = 0
        var preSecond = 1
        var index = 0

        while (index < pos - 1) {
            val temp = preFirst
            preFirst = preSecond
            preSecond += temp
            index++
        }
        return preFirst + preSecond
    }
}

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val pos = 8
            val num = Test().num(pos - 1)
            System.out.print(num)
        }
    }

    fun num(pos: Int): Int {
        var preFirst = 0
        var preSecond = 1
        var index = 0
        while (index < pos - 1) {
            val temp = preFirst
            preFirst = preSecond
            preSecond += temp
            index++
        }
        return preFirst + preSecond
    }
}

class Test2 {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val list = arrayOf(3, 4, 9, 1, 2, 6)
            Test2().sort(list)
            list.forEach {
                System.out.print(it)
            }
        }
    }


    fun sort(list: Array<Int>) {
        for (i in 1 until list.size) {
            for (j in i downTo 1) {
                if (list[j - 1] > list[j]) {
                    swap(list, j, j - 1)
                } else {
                    break
                }
            }
        }
    }

    fun swap(list: Array<Int>, from: Int, to: Int) {
        val temp = list[from]
        list[from] = list[to]
        list[to] = temp
    }
}

class Test3 {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val isRecycle = Test3().isRecycle2("abcb")
            LogUtil.log(if (isRecycle) "true" else "false")
        }
    }

    fun isRecycle(content: String): Boolean {
        var index = 0
        while (index < (content.length) / 2) {
            if (content[index] == content[content.length - 1 - index]) {
                index++
            } else {
                return false
            }
        }
        return true
    }

    fun isRecycle2(content: String): Boolean {
        var index = 0
        while (index < (content.length) / 2) {
            if (content[index] == content[content.length - 1 - index]) {
                index++
            } else {
                return false
            }
        }
        return true
    }
}