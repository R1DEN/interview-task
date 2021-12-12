package md.riden.interviewtask.common

import com.google.gson.GsonBuilder
import io.qameta.allure.Allure
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream

object AllureHelper {
    fun attachObject(name: String, obj: Any) {
        Allure.addAttachment(
            name,
            "application/json",
            GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(obj)
        )
    }

    fun attachScreenshot(name: String, image: BufferedImage) {
        Allure.addAttachment(name, ByteArrayInputStream(ImageHelper.imageToByteArray(image)))
    }
}