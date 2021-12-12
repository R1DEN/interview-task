package md.riden.interviewtask.core

import md.riden.interviewtask.common.logger
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import java.io.File
import java.util.logging.Level

class WebDriverHolder {
    private val chromeCapabilities: Capabilities
        get() {
            val options = ChromeOptions()
            options.addArguments("--disable-dev-shm-usage")
            options.addArguments("--start-maximized")
            options.setExperimentalOption("useAutomationExtension", false)
            val logs = LoggingPreferences().apply {
                enable(LogType.BROWSER, Level.ALL)
                enable(LogType.DRIVER, Level.ALL)
            }
            options.setCapability(CapabilityType.LOGGING_PREFS, logs)
            return options
        }

    private val browserContainer = BrowserWebDriverContainer<Nothing>()
    val driver: WebDriver
        get() = browserContainer.webDriver

    init {
        browserContainer.apply {
            withCapabilities(chromeCapabilities)
            withEnv(
                mapOf(
                    Pair("SCREEN_WIDTH", "1920"),
                    Pair("SCREEN_HEIGHT", "1080")
                )
            )
            val f = File("./target/recodings")
            f.mkdirs()
            withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING, f)
            start()
            followOutput(Slf4jLogConsumer(logger("browserContainer")))
        }
    }

    fun close() {
        browserContainer.webDriver.quit()
        browserContainer.stop()
    }
}
