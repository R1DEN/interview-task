package md.riden.interviewtask.core

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.reflections.Reflections

class PageObjectManager(driver: WebDriver) {
    val poms: MutableMap<Class<out AbstractPom>, AbstractPom> = HashMap()

    init {
        val reflections = Reflections("md.riden.interviewtask.poms")
        val classes = reflections.getSubTypesOf(AbstractPom::class.java)
        classes.filter { !it.kotlin.isAbstract }.forEach { clazz ->
            poms[clazz] = clazz.getConstructor(WebDriver::class.java).newInstance(driver)
                .also { PageFactory.initElements(driver, it) }
        }
    }

    inline fun <reified T : AbstractPom> getPomOf(): T {
        val pom = poms[T::class.java]
        return if (T::class.java.isInstance(pom)) pom as T else throw RuntimeException("something really weird happened in POM Manager")
    }
}
