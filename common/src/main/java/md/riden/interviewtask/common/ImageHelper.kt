package md.riden.interviewtask.common

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

object ImageHelper {
    fun imageToByteArray(image: BufferedImage): ByteArray {
        ByteArrayOutputStream().use { baos ->
            ImageIO.write(image, "png", baos)
            baos.flush()
            return baos.toByteArray()
        }
    }
}