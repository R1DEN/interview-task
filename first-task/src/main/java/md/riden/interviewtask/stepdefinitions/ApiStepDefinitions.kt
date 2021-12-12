package md.riden.interviewtask.stepdefinitions

import com.google.gson.Gson
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import md.riden.interviewtask.api.PhotosAPI
import md.riden.interviewtask.common.AllureHelper
import md.riden.interviewtask.common.logger
import md.riden.interviewtask.context.ApiContext
import md.riden.interviewtask.models.Photo
import org.junit.Assert
import java.lang.Exception
import java.lang.RuntimeException

class ApiStepDefinitions(private val apiContext: ApiContext) {
    @Given("^user gets photos$")
    fun getPhotos() {
        logger().info("Retrieving photos")
        apiContext.photosResponse = PhotosAPI.getPhotos()
        logger().info(apiContext.photosResponse.body.asString())
        apiContext.listOfPhotos = try {
            logger().info("Parsing photos array")
            apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        } catch (e: Exception) {
            throw RuntimeException("Couldn't parse response", e)
        }
    }

    @Then("^user asserts that response code is equal to ([0-9]{3})$")
    fun responseCodeEqualTo(code: Int) {
        apiContext.photosResponse.then().assertThat().statusCode(code)
    }

    @Then("^user asserts that there are more than ([0-9]{1,6}) photos$")
    fun assertThereArePhotos(num: Int) {
        Assert.assertTrue("Photos API response contains 0 array elements", apiContext.listOfPhotos.size > num)
    }

    @Then("^user asserts response body has a photo with \"(.*)\" title$")
    fun checkIfResponseBodyHasPhotoWithTitle(title: String) {
        Assert.assertNotNull(
            "There is no photo with \"$title\" title",
            apiContext.listOfPhotos.firstOrNull { it.title == title })
    }

    @Then("^user removes all photos from the collection that have albumId different than ([0-9]{1,6})$")
    fun removePhotosWithAlbumIdNotEqual(albumId: Int) {
        val filteredPhotos = apiContext.listOfPhotos.filterNot { it.albumId != albumId }
        logger().info("Filtering with ID result: " + Gson().toJson(filteredPhotos))
        AllureHelper.attachObject("Photos with album id equal to $albumId", filteredPhotos)
    }

    @Then("^user removes all photos that do not contain the word \"(.*)\" in the title$")
    fun removePhotosWithNameThatDoesntContain(title: String) {
        val filteredPhotos = apiContext.listOfPhotos.filterNot { !it.title.contains(title) }
        logger().info("Filtering with title result " + Gson().toJson(filteredPhotos))
        AllureHelper.attachObject("Photos that contain \"$title\" in title", filteredPhotos)

    }
}