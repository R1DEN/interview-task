package md.riden.interviewtask.core

import org.openqa.selenium.WebDriver

open class AbstractSteps(protected val context: DigestContext) {
    protected val webDriver: WebDriver by lazy { context.holder.driver }
}
