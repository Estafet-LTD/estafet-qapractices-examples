/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/resources/future",
                 glue = {"com.estafet.qapractices.test",
                         "com.estafet.qapractices.api"},
                 tags = "@rest",
                 plugin = {"pretty", "html:target/cucumber-report"}    )
public class TestRunner {




}
