package md.riden.interviewtask.stepDefinitions

import com.google.gson.Gson
import io.cucumber.java.en.When
import md.riden.interviewtask.common.logger
import md.riden.interviewtask.context.ApiContext
import md.riden.interviewtask.api.PhotosAPI
import md.riden.interviewtask.models.Photo
import org.junit.Assert

class ApiStepDefinitions(private val apiContext: ApiContext) {
    @When("user gets photos")
    fun getPhotos() {
        apiContext.photosResponse = PhotosAPI.getPhotos()
        logger().info(apiContext.photosResponse.body.asString())
    }

    @When("user 2")
    fun partTwo() {
        apiContext.photosResponse.then().assertThat().statusCode(200)
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        Assert.assertTrue("Photos API response contains 0 array elements", list.size > 0)
        Assert.assertNotNull(
            "There is no photo with \"non sit quo\" title",
            list.firstOrNull { it.title == "non sit quo" })
    }

    @When("user 3")
    fun partThree() {
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        val listOfAlbumId100 = list.filterNot { it.albumId != 100 }
        println(Gson().toJson(listOfAlbumId100))
        val listOfErrorPhotos = list.filterNot { !it.title.contains("error") }
        println(Gson().toJson(listOfErrorPhotos))
    }
}