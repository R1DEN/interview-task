package md.riden.interviewtask.poms

import md.riden.interviewtask.core.AbstractPom
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PointMdHomePage(webDriver: WebDriver) : AbstractPom(webDriver) {
    @FindBy(xpath = "//article[parent::div[not(@id)]]/parent::div/preceding-sibling::div")
    private lateinit var newsHeadline: WebElement

    fun scrollToNewsHeadline() {
        (webDriver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView()", newsHeadline)
    }

    fun navigateTo() {
        webDriver.navigate().to("https://point.md")
    }
}