import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features"],
    plugin = ["pretty", "json:target/cucumber.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"],
    glue = ["md.riden.interviewtask.context", "md.riden.interviewtask.stepdefinitions"],
    tags = "@Task2"
)

class CucumberRunnerTest