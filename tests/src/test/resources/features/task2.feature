@Task2
Feature: Task2

  Scenario: Framework gathers currency rates, news headlines and feeds them to the chat
    Given framework gets all latest currency rates
    When framework gets local news headlines
    Then framework feeds digest to "-546382550" chat