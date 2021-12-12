package md.riden.interviewtask.core

import md.riden.interviewtask.cursmd.models.BankList
import ru.yandex.qatools.ashot.Screenshot

class DigestContext {
    lateinit var rawCursMdResponse: BankList
    lateinit var newsHeadline: Screenshot


    val holder: WebDriverHolder by lazy { WebDriverHolder() }
    val pomManager: PageObjectManager by lazy { PageObjectManager(holder.driver) }
}