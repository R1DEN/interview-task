package md.riden.interviewtask.poms

import md.riden.interviewtask.common.logger
import md.riden.interviewtask.core.AbstractPom
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PointMdHomePage(webDriver: WebDriver) : AbstractPom(webDriver) {
    @FindBy(xpath = "//article[parent::div[not(@id)]]/parent::div/preceding-sibling::div")
    private lateinit var newsHeadline: WebElement

    fun scrollToNewsHeadline() {
        logger().info("Scrolling to news headline")
        (webDriver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView()", newsHeadline)
        logger().info("Finished scrolling to news headline")
    }

    fun navigateTo() {
        logger().info("Navigating to point.md")
        webDriver.navigate().to("https://point.md")
        logger().info("Finished navigating to point.md")
    }
}