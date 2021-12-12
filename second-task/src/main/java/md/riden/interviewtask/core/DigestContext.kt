package md.riden.interviewtask.core

import md.riden.interviewtask.cursmd.models.BankList
import org.openqa.selenium.WebDriver

class DigestContext {
    val messagesToSend = mutableListOf<String>()
    lateinit var rawCursMdResponse: BankList
    val holder = WebDriverHolder()
    val driver: WebDriver by lazy { holder.driver }
}