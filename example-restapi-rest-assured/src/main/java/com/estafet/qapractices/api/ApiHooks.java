package com.estafet.qapractices.api;


import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.core.ReqRest;
import com.estafet.qapractices.env.Environment;
import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.java.guice.ScenarioScoped;
import io.restassured.RestAssured;


@ScenarioScoped
public class ApiHooks {
	
	private Context context;
	private Environment env;
	
	@Inject
	public ApiHooks(Context context,Environment env ) {
		this.context = context; 
		this.env = env;
		
	}
	@Before
    public void setScenario(final Scenario scenario) {
        this.context.setScenario(scenario);
    }
	
	@Before("@rest")
	public void apiData() {
		System.out.println("Start test");
		/*
		 * baseURI method will set globally the URI that you want to connect. It have to
		 * be setted into a constructor or method. After that in given() method of
		 * RestAssured class, this URI will be loaded automatically.
		 * basePath method will set the end point for the API.
		 */
		
		RestAssured.baseURI = env.getProperty("baseURI"); 
		RestAssured.basePath = env.getProperty("basePath");
		
	}
	@After("@rest")
	public void after() {
		System.out.println("End of test");
	}

}
