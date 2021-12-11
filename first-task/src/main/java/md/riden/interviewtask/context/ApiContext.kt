package md.riden.interviewtask.context

import io.restassured.response.Response
import md.riden.interviewtask.models.Photo

class ApiContext {
    lateinit var photosResponse: Response
    lateinit var listOfPhotos: List<Photo>
}