package md.riden.interviewtask.common

import com.google.gson.GsonBuilder
import io.qameta.allure.Allure

object AllureHelper {
    fun attachObject(name: String, obj: Any) {
        Allure.addAttachment(
            name,
            "application/json",
            GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(obj)
        )
    }
}