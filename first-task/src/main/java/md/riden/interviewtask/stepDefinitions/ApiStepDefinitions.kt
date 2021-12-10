package md.riden.interviewtask.stepDefinitions

import com.google.gson.Gson
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import md.riden.interviewtask.api.PhotosAPI
import md.riden.interviewtask.common.logger
import md.riden.interviewtask.context.ApiContext
import md.riden.interviewtask.models.Photo
import org.junit.Assert

class ApiStepDefinitions(private val apiContext: ApiContext) {
    @Given("user gets photos")
    fun getPhotos() {
        apiContext.photosResponse = PhotosAPI.getPhotos()
        logger().info(apiContext.photosResponse.body.asString())
    }

    @Then("^user asserts that response code is equal to ([0-9]{3})$")
    fun responseCodeEqualTo(code: Int) {
        apiContext.photosResponse.then().assertThat().statusCode(code)
    }

    @Then("^user asserts that there are more than ([0-9]{1,6}) photos$")
    fun assertThereArePhotos(num: Int) {
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        Assert.assertTrue("Photos API response contains 0 array elements", list.size > num)

    }

    @Then("^user asserts response body has a photo with \"(.*)\" title$")
    fun checkIfResponseBodyHasPhotoWithTitle(title: String) {
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        Assert.assertNotNull(
            "There is no photo with \"$title\" title",
            list.firstOrNull { it.title == title })
    }

    @Then("^user removes all photos from the collection that have albumId different than ([0-9]{1,6})$")
    fun removePhotosWithAlbumIdNotEqual(albumId: Int) {
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        val listOfAlbumId100 = list.filterNot { it.albumId != albumId }
        println(Gson().toJson(listOfAlbumId100))

    }

    @Then("^user removes all photos that do not contain the word \"(.*)\" in the title$")
    fun removePhotosWithNameThatDoesntContain(title: String) {
        val list = apiContext.photosResponse.then().extract().body().jsonPath().getList(".", Photo::class.java)
        val listOfErrorPhotos = list.filterNot { !it.title.contains(title) }
        println(Gson().toJson(listOfErrorPhotos))
    }
}