package md.riden.interviewtask.cursmd.core

import md.riden.interviewtask.cursmd.models.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.regex.Pattern

object CursXmlParser {
    fun parseHtml(html: String): BankList {
        val doc = Jsoup.parse(html)
        val rows = doc.select("#tabelBankValute > tbody").select("tr")
        val banks = ArrayList<Bank>()
        rows.forEach { banks.add(parseBank(it)) }
        return BankList(banks)
    }

    private fun parseBank(row: Element): Bank {
        val columns = row.select("td")
        return Bank(
            getBankNameForRow(columns[0]),
            getCsvType(columns[0]),
            getLocality(columns[0]),
            extractRatesFromColumns(columns)
        )
    }

    private fun getBankNameForRow(column: Element) = column.selectFirst("a")!!.text()

    private fun extractRatesFromColumns(columns: Elements): ArrayList<Currency> {
        val currencies = ArrayList<Currency>()
        for (i in 1 until columns.size step 2) {
            val code = parseCurrencyCode(columns[i].attr("class"))
            code?.let {
                val buy = columns[i].text()
                val sell = columns[i + 1].text()
                if (buy != "-" && sell != "-")
                    currencies.add(
                        Currency(
                            code,
                            Rate(buy.replace(',', '.').toFloat(), sell.replace(',', '.').toFloat())
                        )
                    )
            }
        }
        return currencies
    }

    private fun getLocality(column: Element) = column.ownText().let {
        if (it.isNullOrEmpty()) "Chisinau" else "(?<=\\().*(?=\\))".toRegex().find(it)?.value ?: ""
    }


    private fun getCsvType(column: Element) = CSVType.parse(
        column.selectFirst("span")!!.classNames().first { !it.equals("badge", ignoreCase = true) })

    private fun parseCurrencyCode(input: String): String? {
        val pattern = Pattern.compile("(?<=column-)(.{3})(?= )")
        val matcher = pattern.matcher(input)
        if (matcher.find()) {
            return matcher.group(0)
        }
        return null
    }
}