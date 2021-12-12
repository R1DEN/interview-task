package md.riden.interviewtask.cursmd.core

import md.riden.interviewtask.common.logger
import md.riden.interviewtask.cursmd.models.BankList
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CursMd {
    private val endpoint = "https://www.curs.md/ro/ajax/block?block_name=bank_valute_table&referer=curs_valutar_banci"

    //Using HttpClient because RestAssured doesn't work. Tried with Postman echo, and the requests were exactly the
    //same except "accept" header
    fun getBankListForDate(date: LocalDate): BankList {
        val request = createRequest(date)
        val resp = simulateAjaxRequest(request)
        return parseResponse(resp)
    }

    private fun createRequest(date: LocalDate): HttpPost {
        val request = HttpPost(endpoint)
        request.entity = UrlEncodedFormEntity(
            mutableListOf<NameValuePair>(
                BasicNameValuePair("CotDate", date.format(DateTimeFormatter.ISO_DATE)),
                BasicNameValuePair("region", "all"),
                BasicNameValuePair("ofType", "all")
            )
        )
        request.addHeader("Content-Type", "application/x-www-form-urlencoded")
        return request
    }

    private fun simulateAjaxRequest(request: HttpPost): String {
        val client = HttpClients.createDefault()
        logger().info("Getting data form curs.md AJAX request")
        return try {
            String(client.execute(request).entity.content.readAllBytes())
        } catch (e: Exception) {
            logger().error("Could not get data from curs.md AJAX request")
            throw RuntimeException(e)
        }
    }

    private fun parseResponse(resp: String): BankList {
        logger().info("Parsing curs.md AJAX response")
        return try {
            CursXmlParser.parseHtml(resp)
        } catch (e: Exception) {
            logger().error("Could not parse data from curs.md AJAX response")
            throw RuntimeException(e)
        }
    }
}