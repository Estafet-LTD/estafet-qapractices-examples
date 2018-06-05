package com.estafet.qapractices.test;


import java.util.List;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.core.ReqRest;
import com.estafet.qapractices.model.ReqResUserModel;
import com.google.inject.Inject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefReqRest {
	private ReqRest reqRest;
	private Context context;
	
	
	@Inject
	public StepDefReqRest(ReqRest reqRest,Context context) {
		this.reqRest = reqRest;
		this.context = context;
				
	}
	
	@Given("^user send a get request$")
	public void sendingGetRequest() {
	   reqRest.getRequestRestApi();
	    
	}

	@Given("^the status for get request is valid$")
	public void assertGetStatus() {
	    reqRest.getResponseCode();
	}
	
	@Given("^user list all users$")
	public void listAllUsers()  {
	   List<ReqResUserModel> users = reqRest.listAllUsers().getData();
	   context.saveData("users", users);
	   
	}

	@When("^user send a post request to create \"([^\"]*)\"$")
	public void sendingPostRequest(String name)  {
	    reqRest.postRequestRestApi(name);
	}

	@Then("^chek the status for post request is valid$")
	public void assertPostStatus()  {
	    reqRest.postResponseCode();
	}
	
	@When("^user check if \"([^\"]*)\" as user exist and collect user ID$")
	public void checkUserAndGetUserId(String name)  {
	    reqRest.chekUserAndGetId(name);
	    
	}

	@Then("^user can be updated to \"([^\"]*)\"$")
	public void sendingPutRequest(String name) throws Throwable {
		reqRest.putRequestRestApiById(name);
	}
	
	@Then("^user can be deleted$")
	public void sendingDeleteRequest()  {
	    reqRest.deleteUserById();
	}
	@Given("^user send a get request and status is valid$")
	public void sendingGetRequestAndAssert() {
	    reqRest.getReqWithVerify();
	}


}
