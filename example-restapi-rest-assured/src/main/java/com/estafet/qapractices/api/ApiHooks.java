package com.estafet.qapractices.api;


import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.core.ReqRest;
import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.java.guice.ScenarioScoped;
import io.restassured.RestAssured;


@ScenarioScoped
public class ApiHooks {
	private ReqRest request;
	private Context context;
	
	@Inject
	public ApiHooks(Context context) {
		this.context = context; 
		
	}
	@Before
    public void setScenario(final Scenario scenario) {
        this.context.setScenario(scenario);
    }
	
	@Before("@rest1")
	public void apiData() {
		System.out.println("Start test");
		RestAssured.basePath = "https://reqres.in/api"; 
		
	}
	@After("@rest1")
	public void after() {
		System.out.println("End of test");
	}

}
