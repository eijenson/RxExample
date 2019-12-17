package floatingPointNumber

import java.math.BigDecimal
import kotlin.math.round

class FloatingPointNumber {
    // 値引き後値段 = 定価 - (定価 * 割引率 / 100.0)
    fun run() {
        for (p in 0..100000) {
            val price = p.toDouble()
            for (w in 0..100) {
                val wari = w.toDouble()
                val sum: Double = keisan1(price, wari)
                val sum2 = keisan2(price, wari)
                val sum3 = keisan3(price, wari)
                if (sum % 1 != 0.0) {
                    if (round(sum) != round(sum2)) println("$price $wari = $sum:$sum2:$sum3")
                }
            }
        }
    }

    // 値引き後値段 = 定価 - (定価 * 割引率 / 100.0)
    private fun keisan1(price: Double, wari: Double): Double {
        val discount = price * wari / 100
        return price - discount
    }

    // 値引き後値段 = 定価 - (定価 * 割引率 / 100.0)
    private fun keisan2(price: Double, wari: Double): Double {
        val p2 = BigDecimal.valueOf(price)
        val w2 = BigDecimal.valueOf(wari)
        val discount2 = p2 * w2 / BigDecimal.valueOf(100)
        //val sum = p2 - discount2
        //println(sum)
        return p2.minus(discount2).toDouble()
    }

    // 値引き後値段 = 定価 - (定価 * (割引率 / 100.0))
    private fun keisan3(price: Double, wari: Double): Double {
        val discount = price * (wari / 100)
        return price - discount
    }
}