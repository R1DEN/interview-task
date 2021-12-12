package md.riden.interviewtask.stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import md.riden.interviewtask.common.AllureHelper
import md.riden.interviewtask.common.ImageHelper
import md.riden.interviewtask.core.AbstractSteps
import md.riden.interviewtask.core.DigestContext
import md.riden.interviewtask.cursmd.core.CursMd
import md.riden.interviewtask.poms.PointMdHomePage
import md.riden.interviewtask.telegram.Bot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.yandex.qatools.ashot.AShot
import java.io.ByteArrayInputStream
import java.time.LocalDate

class DigestStepDefinitions(context: DigestContext) : AbstractSteps(context) {
    private val pointMdHomePage: PointMdHomePage by lazy { context.pomManager.getPomOf() }

    @Given("^framework gets all latest currency rates$")
    fun frameworkGetsAllLatestCurrencyRates() {
        context.rawCursMdResponse = CursMd().getBankListForDate(LocalDate.now())
        AllureHelper.attachObject("Currency Rates", context.rawCursMdResponse)
    }

    @When("^framework gets local news headlines$")
    fun frameworkGetsLocalNewsHeadline() {
        pointMdHomePage.navigateTo()
        pointMdHomePage.scrollToNewsHeadline()
        val screenshot = AShot().takeScreenshot(webDriver)
        context.newsHeadline = screenshot
        AllureHelper.attachScreenshot("news", screenshot.image)
    }

    @Then("^framework feeds digest to \"(.*)\" chat$")
    fun sendMessagesToChat(chatId: String) {
        val bot = Bot()
        TelegramBotsApi(DefaultBotSession::class.java).registerBot(bot)
        context.rawCursMdResponse.getBankByName("Banca Nationala").toString().let {
            bot.execute(SendMessage(chatId, it))
        }
        ByteArrayInputStream(ImageHelper.imageToByteArray(context.newsHeadline.image)).let {
            bot.execute(SendPhoto(chatId, InputFile(it, "news")))
        }
    }
}