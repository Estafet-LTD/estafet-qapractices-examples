package com.estafet.qapractices.testRunner;

/**
 * Created by Pesho on 01-Sep-17.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * The test runner class. This is the class you need to run, when executing tests.
 * Right click on it and press Run.
 * Be aware of the following:
 * glue - path to the step definitions. It will work OOTB if you put your stepdefs in the already defined package.
 * You can add more- use the syntax shown below, separate the strings with a comma.
 * features - path to feature files. You can add new. The rules for the "glue" apply here also.
 * plugin - just don't touch.
 * tags - this is the part you will be editing most of the time. All tests should be tagged (in this context is tag,
 * not annotation, shut up) and ran through here. You can have multiple tags ran in the same run:
 * {"@Rest, @UI, @DB"}
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.estafet.qapractices.stepDefs",
                "com.estafet.qapractices.hooks"
        },
        features = {"src/test/resources/features/"},
        plugin = {
                "pretty", "html:target/cucumber-reports",
                "json:target/cucumber-reports/cucumber.json"},
        tags = {"@Json"}
)
//Always end Junit test class with the word "Test" if you want to run it with Maven!
public class RunTests {
}
