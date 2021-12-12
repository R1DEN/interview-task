package md.riden.interviewtask.core

import io.cucumber.java.After

class CucumberHooks(private val ctx: DigestContext) {
    @After
    fun tearDown() {
        ctx.holder.close()
    }
}