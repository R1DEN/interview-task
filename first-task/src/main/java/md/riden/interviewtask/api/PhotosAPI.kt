package md.riden.interviewtask.api

import io.restassured.RestAssured
import io.restassured.response.Response
import md.riden.interviewtask.common.logger

object PhotosAPI {
    private const val url = "https://jsonplaceholder.typicode.com"
    private val baseRequest
        get() = RestAssured.given().baseUri(url)

    fun getPhotos(): Response {
        logger().info("Making request to /photos endpoint")
        return baseRequest.get("/photos")
    }
}
