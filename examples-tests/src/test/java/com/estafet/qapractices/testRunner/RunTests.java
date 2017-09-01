package com.estafet.qapractices.testRunner;

/**
 * Created by Pesho on 01-Sep-17.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.estafet.qapractices.stepDefs"
        },
        features = {"src/test/resources/features/"},
        plugin = {
                "pretty", "html:target/cucumber-reports",
                "json:target/cucumber-reports/cucumber.json"},
        tags = {"@Example"}
)
//Always end Junit test class with the word "Test" if you want to run it with Maven!
public class RunTests {
}
