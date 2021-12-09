package md.riden.task1.api

import io.restassured.RestAssured
import io.restassured.response.Response

object PhotosAPI {
    private const val url = "https://jsonplaceholder.typicode.com"
    private val baseRequest
        get() = RestAssured.given().baseUri(url)

    fun getPhotos(): Response {
        return baseRequest.get("/photos")
    }
}
