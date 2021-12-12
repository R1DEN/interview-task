package md.riden.interviewtask.common

import com.google.gson.GsonBuilder
import io.qameta.allure.Allure
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream

object AllureHelper {
    fun attachObject(name: String, obj: Any) {
        attachJson(name, GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(obj))
    }

    fun attachJson(name: String, json: String) {
        Allure.addAttachment(name, "application/json", json)
    }

    fun attachScreenshot(name: String, image: BufferedImage) {
        Allure.addAttachment(name, ByteArrayInputStream(ImageHelper.imageToByteArray(image)))
    }
}