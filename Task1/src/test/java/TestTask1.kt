import com.google.gson.Gson
import md.riden.task1.api.PhotosAPI
import md.riden.task1.models.Photo
import org.junit.Assert
import org.junit.Test

class TestTask1 {
    @Test
    fun partOne() {
        val response = PhotosAPI.getPhotos()
        println(Gson().toJson(response.body.print()))
    }

    @Test
    fun partTwo() {
        val response = PhotosAPI.getPhotos()
        response.then().assertThat().statusCode(200)
        val list = response.then().extract().body().jsonPath().getList(".", Photo::class.java)
        Assert.assertTrue("Photos API response contains 0 array elements", list.size > 0)
        Assert.assertNotNull(
            "There is no photo with \"non sit quo\" title",
            list.firstOrNull { it.title == "non sit quo" })
    }

    @Test
    fun partThree() {
        val response = PhotosAPI.getPhotos()
        val list = response.then().extract().body().jsonPath().getList(".", Photo::class.java)
        val listOfAlbumId100 = list.filterNot { it.albumId != 100 }
        println(Gson().toJson(listOfAlbumId100))
        val listOfErrorPhotos = list.filterNot { !it.title.contains("error") }
        println(Gson().toJson(listOfErrorPhotos))
    }
}