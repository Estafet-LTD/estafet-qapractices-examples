/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.tests.runner;

/**
 * Created by Unit 1 on 02-Jul-17.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * JUnit runner file. If you need to know how it works, there are comments in the examples-rest module
 * And don't skip modules!
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.estafet.qapractices.tests.testhooks",
                "com.estafet.qapractices.tests.stepdefs"
        },
        features = {"src/test/resources/features/"},
        plugin = {
                "pretty", "html:target/cucumber-reports",
                "json:target/cucumber-reports/cucumber.json"},
        tags = {"@Wiki"}
)
public class RunSeleniumTest {

        private RunSeleniumTest () {

        }
        /**
         * This is a JUnit annotation NOT Cucumber. It will be called after the end of the last scenario
         * and will close the geckodriver instance. Otherwise you will have an open instance after every execution.
         */
        @AfterClass
        public static void closeBrowser() throws IOException {
                Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
        }
}
