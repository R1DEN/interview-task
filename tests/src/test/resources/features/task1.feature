Feature: Task1

  Scenario: Task1
    Given user gets photos
    Then user asserts that response code is equal to 200
    Then user asserts that there are more than 0 photos
    Then user asserts response body has a photo with "non sit quo" title
    Then user removes all photos from the collection that have albumId different than 100
    Then user removes all photos that do not contain the word "error" in the title
