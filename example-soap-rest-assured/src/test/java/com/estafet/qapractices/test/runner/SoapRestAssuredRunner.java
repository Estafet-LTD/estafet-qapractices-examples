/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/*
 * Created by Anton Rusanov 31.05.2018
 */
@RunWith(Cucumber.class)
@CucumberOptions( glue = {"com.estafet.qapractices.stepdef",
                          "com.estafet.qapractices.hooks"},
        features = {"src/test/resources/features/"},
        plugin = {
                "pretty", "html:target/cucumber-reports",
                "json:target/cucumber-reports/cucumber.json"},
        tags = {"@soap"})
public class SoapRestAssuredRunner {



}
