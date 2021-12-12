package md.riden.interviewtask.stepdefinitions

import com.google.gson.Gson
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import md.riden.interviewtask.common.AllureHelper
import md.riden.interviewtask.common.ImageHelper
import md.riden.interviewtask.core.DigestContext
import md.riden.interviewtask.cursmd.core.CursMd
import md.riden.interviewtask.telegram.Bot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.yandex.qatools.ashot.AShot
import java.io.ByteArrayInputStream
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
        Gson().toJson(context.rawCursMdResponse.getRatesForBank("Banca Nationala")).asSequence()
            .windowed(2048, 2048, partialWindows = true).forEach {
                val kek = String(it.toCharArray())
                println(kek)
                bot.execute(SendMessage(chatId, kek))
            }
        context.driver.navigate().to("https://point.md")
        val screenshot = AShot().takeScreenshot(context.driver)
        AllureHelper.attachScreenshot("news", screenshot.image)
        bot.execute(
            SendPhoto(
                chatId,
                InputFile(ByteArrayInputStream(ImageHelper.imageToByteArray(screenshot.image)), "news")
            )
        )
    }

}