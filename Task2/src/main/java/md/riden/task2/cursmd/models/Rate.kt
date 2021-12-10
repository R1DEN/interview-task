package md.riden.task2.cursmd.models

import java.text.DecimalFormat

class Rate(val buy: Float, val sell: Float) {
    override fun toString(): String {
        val df = DecimalFormat("0.00##")
        return "${df.format(buy)} : ${df.format(sell)}"
    }
}