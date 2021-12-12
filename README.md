
# Interview task
## Tools used:
- [Kotlin](https://kotlinlang.org/)
- [JUnit](https://junit.org/junit4/)
- [Maven](https://maven.apache.org/)
- [Cucumber](https://cucumber.io/)
- [PicoContainer](http://picocontainer.com/)
- [Selenium](https://www.selenium.dev/)
- [Testcontainers](https://www.testcontainers.org/)
- [Docker](https://www.docker.com/)
- [RestAssured](https://rest-assured.io/)
- [Log4j2](https://logging.apache.org/log4j/2.x/)
- [Allure](https://github.com/allure-framework/allure-java)
- [Telegram](https://telegram.org/). [Rubenlagus Library](https://github.com/rubenlagus/TelegramBots)

## Implemented tasks:
- Task 1 - just as written in the interview task itself.
- Task 2 - digest automation framework which gets currency rates and headline news from local websites and passes them to a defined chat in Telegram.

## Technical description:
#### General:
- This implementation uses Cucumber framework to run test scenarios written in Gherkin language;
- Context sharing and dependency injection via Cucumber PicoContainer;
- Logging is made with the help of Log4j2 library;
- Reporting is provided by Allure;
- Tests are run via JUnit in Maven (surefire plugin).

#### First task:
First task is implemented using RestAssured library.

#### Second task:
- In the first part the framework emulates an AJAX request on curs.md website to get detailed info on the local currency rates. Requests are made with Apache Http Client.  
- The second parts opens a Chrome WebDriver in Docker using Testcontainers to open the homepage of the most popular local news site, scroll to the main articles and make an overview screenshot.
In case a "test" fails - a video will be recorded and saved under tests/target/recordings folder which will help diagnose the issue.
- The third part combines the currency rates from the National Bank and the News and sends them to a designated chat in Telegram using the Telegram Bot API.

## How to use:
### Requirements:
- Java 11;
- Docker;
- Allure CLI;
- Internet connection.
### Usage:
BOT_TOKEN will be shared via email.

1. Join the telegram channel via the link in email.
2. Clone the repository:
```
   git clone https://github.com/R1DEN/interview-task.git  
```
3. Run tests via Maven:
```
   mvn test -Dtest=CucumberRunnerTest -Dbot_token=BOT_TOKEN
```  
(Optional) You can filter out which tests to run using the tag system:
```  
   -D"cucumber.filter.tags=@Task1 or @Task2"
 ```
3. Read the results.
   If you have Allure CLI already installed, just go to the "tests" directory and use this command:
```
   allure serve
```
This will open the report in a new browser window.