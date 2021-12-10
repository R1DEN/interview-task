package md.riden.task2.cursmd.core

import md.riden.task2.cursmd.models.BankList
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CursMd {
    private val endpoint = "https://www.curs.md/ro/ajax/block?block_name=bank_valute_table&referer=curs_valutar_banci"

    @Throws(IOException::class)
    fun getBankListForDate(date: LocalDate): BankList {
        //Using HttpClient because RestAssured doesn't work. Tried with Postman echo, and the requests were exactly the
        //same except "accept" header
        val httpClient = HttpClients.createDefault()
        val request = HttpPost(endpoint)
        request.entity = UrlEncodedFormEntity(
            mutableListOf<NameValuePair>(
                BasicNameValuePair("CotDate", date.format(DateTimeFormatter.ISO_DATE)),
                BasicNameValuePair("region", "all"),
                BasicNameValuePair("ofType", "all")
            )
        )
        request.addHeader("Content-Type", "application/x-www-form-urlencoded")
        val resp = String(httpClient.execute(request).entity.content.readAllBytes())
        return CursXmlParser.parseHtml(resp)
    }

}