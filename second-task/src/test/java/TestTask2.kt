import com.google.gson.Gson
import md.riden.interviewtask.cursmd.core.CursMd
import md.riden.interviewtask.telegram.Bot
import org.junit.Test
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.time.LocalDate

class TestTask2 {
    @Test
    fun task2() {
        val ekk = CursMd().getBankListForDate(LocalDate.now())
        val bot = Bot()
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(bot)
        Gson().toJson(ekk).asSequence().windowed(2048, 2048).forEach {
            bot.execute(SendMessage("-546382550", String(it.toCharArray())))
        }

//
        println("done")
    }
}