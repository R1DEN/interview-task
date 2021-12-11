package md.riden.interviewtask.context

import md.riden.interviewtask.cursmd.models.BankList

class DigestContext {
    val messagesToSend = mutableListOf<String>()
    lateinit var rawCursMdResponse: BankList
}