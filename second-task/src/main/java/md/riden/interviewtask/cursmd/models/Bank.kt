package md.riden.interviewtask.cursmd.models


class Bank(val name: String, val type: CSVType, val locality: String, val currencies: List<Currency>) {
    fun getRateFor(currency: String): Rate {
        return currencies.find { it.name == currency }?.rate ?: throw Exception("Bank doesn't have this currency")
    }

    override fun toString(): String {
        val sb = StringBuilder()
        currencies.forEach { sb.append(it.toString()).append("\n") }
        return "$name\n" + String(sb)
    }

    fun hasRateFor(currency: String): Boolean {
        currencies.forEach { if (it.name == currency) return true }
        return false
    }
}