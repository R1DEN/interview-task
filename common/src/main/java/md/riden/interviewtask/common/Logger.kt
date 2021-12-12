package md.riden.interviewtask.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun <T : Any> T.logger(): Logger = LoggerFactory.getLogger(javaClass)
fun <T : Any> T.logger(name: String): Logger = LoggerFactory.getLogger(name)
