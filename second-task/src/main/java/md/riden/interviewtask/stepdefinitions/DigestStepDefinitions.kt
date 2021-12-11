package md.riden.interviewtask.stepdefinitions

import com.google.gson.Gson
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import md.riden.interviewtask.context.DigestContext
import md.riden.interviewtask.cursmd.core.CursMd
import md.riden.interviewtask.telegram.Bot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.time.LocalDate

class DigestStepDefinitions(private val context: DigestContext) {
    @Given("^framework gets all latest currency rates$")
    fun frameworkGetsAllLatestCurrencyRates() {
        context.rawCursMdResponse = CursMd().getBankListForDate(LocalDate.now())
    }

    @Then("^framework feeds rates from National Bank to \"(.*)\" chat$")
    fun sendMessagesToChat(chatId: String) {
        val bot = Bot()
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(bot)
        Gson().toJson(context.rawCursMdResponse.getRatesForBank("Banca Nationala")).asSequence().windowed(2048, 2048).forEach {
            bot.execute(SendMessage(chatId, String(it.toCharArray())))
        }
        Thread.sleep(5000)
    }

}