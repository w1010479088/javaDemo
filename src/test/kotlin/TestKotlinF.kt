package test.kotlin

//fun <T> ObjectOutputStream.findParentOfType(clazz: Class<test.kotlin.T>): T? {
//    var p = parent
//    while(p != null && !clazz.isInstance(p)){
//        p = p.parent
//    }
//    ObjectOutputStream::class::java
//    return p as T?
//}
fun main(args: Array<String>) {
    TestKotlinF().b()
}

class TestKotlinF {
    fun a() {
        val words = "A long time ago in a galaxy far far away".split(" ")
        val shortWords = mutableListOf<String>()
        words.getShortWordsTo(shortWords, 3)
        println(shortWords)
    }

    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        this.filterTo(shortWords) { it.length <= maxLength }
        val articles = setOf("a", "A", "an", "An", "the", "The")
        shortWords -= articles
    }

    fun b() {
        val list = mutableListOf("A", "D", "E", "F", "N")
        val set = setOf("A", "F")
        val pair: Pair<String, String> = "k1" to "v1"
        val map = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val map2 = mutableMapOf<String, String>().apply {
            this["one"] = "1"
            this["two"] = "2"
            this["three"] = "3"
        }
        val eList = emptyList<String>()
        val eSet = emptySet<String>()
        val eMap = emptyMap<String, String>()

        list -= set
        println(list)
    }

    fun c() {

    }
}