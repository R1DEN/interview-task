package md.riden.interviewtask.common

import org.apache.logging.log4j.LogManager.getLogger
import org.apache.logging.log4j.Logger

fun <T : Any> T.logger(): Logger = getLogger(javaClass)
