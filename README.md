# Estafet QA practices examples
This project contains a set of examples for different aspects of the QA job. It's a multi module Maven project with separate modules demonstrating how to test:
ReST services
User interface (via Selenium)
SOAP services

# Project structure
| Module | Description |
| --- | --- |
| example-restapi-rest-assured | Set of examples for ReST testing using RestAssured |
| example-soap-rest-assured | Set of examples for SOAP testing using RestAssured |
| examples-rest | Set of examples for ReST testing using RestEasy |
| examples-selenium | Set of examples for UI testing using Selenium |
| examples-soap2 | Set of examples for SOAP testing |
| examples-soap-ws | Set of examples for SOAP testing (alternative approach |

# Getting started
After cloning the repository you will be able to run the tests from each module. No additional configuration is required.
Upon cloning execute:
```
mvn clean install
```
Each module has a `RunTests` JUnit class which executed a subset of tests from the current module based nad the `tags` option.
## For Linux/Mac
The Selenium tests will not run on those two OSes. The geckodriver used for Selenium 3 is only provided for Windows.
