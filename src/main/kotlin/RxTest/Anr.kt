package RxTest

import java.util.*

class Anr {
    fun run() {
        stringEquals()
    }

    fun stringEquals() {
        println(System.currentTimeMillis())
        val str = "a" * 100000
        println(str)
        println(System.currentTimeMillis())
    }

    operator fun String.times(num: Int): String {
        val str = buildString {
            for (x in 1..num) {
                append(this@times)
            }
        }

        return str
    }
}