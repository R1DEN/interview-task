# Interview task
## Tools used:
- Kotlin
- JUnit
- Maven
- Cucumber
- Selenium
- Testcontainers
- RestAssured
- Log4j2
- Allure

## Implemented tasks:
- Task 1 - just as written in the task itself.
- Task 2 - digest automation framework which gets currency rates and headline news from local websites and passes them to a defined chat in Telegram.

## Technical description:
- This implementation uses Cucumber framework to run test scenarios written in Gherkin language.
- Logging is made with the help of Log4j2 library
- Reporting is provided by Allure
- Selenium Webdriver handles the browser navigation, while being containerized in Docker with the help of Testcontainers
- Tests are run via JUnit in Maven (surefire plugin)

#### First task
First task is implemented exactly as in the description using Gherkin.

#### Second task
In the first part the framework emulates an AJAX request on curs.md website to get detailed info on the local currency rates. Requests are made with Apache Http Client.
The second parts opens a Chrome WebDriver in Docker using Testcontainers to open the homepage of the most popular local news site , scroll to the main articles and make an overview screenshot
The third part combines the currency rates from the National Bank and the News and sends them to a designated chat in Telegram using the Telegram Bot API

## How to use:
### Requirements:
- Java 11
- Docker
- Internet connection
### Usage:
BOT_TOKEN will be shared via email
```   
    git pull
    mvn test -Dtest -Dtest=CucumberRunnerTest -Dbot_token=BOT_TOKEN
```
You can filter out which tests to run using the tag system:
```
    -D"cucumber.filter.tags=@Task1 OR @Task2"
```