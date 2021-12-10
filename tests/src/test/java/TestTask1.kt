import com.google.gson.Gson
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import md.riden.interviewtask.common.logger
import md.riden.interviewtask.api.PhotosAPI
import md.riden.interviewtask.models.Photo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features"],
    plugin = ["pretty", "json:target/cucumber.json"],
    glue = ["md.riden.interviewtask.context", "md.riden.interviewtask.stepDefinitions"],
)
class TestTask1