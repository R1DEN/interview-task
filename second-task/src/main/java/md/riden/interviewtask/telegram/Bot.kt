package md.riden.interviewtask.telegram

import com.google.gson.Gson
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class Bot : TelegramLongPollingBot() {
    override fun getBotToken() = System.getProperty("bot_token")!!
    override fun getBotUsername() = "riden_digest_bot"
    override fun onUpdateReceived(update: Update) {
    }
}