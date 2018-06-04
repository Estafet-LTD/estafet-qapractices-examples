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
	public void user_send_a_get_request() throws Exception {
	   reqRest.getRequestRestApi();
	    
	}

	@Given("^the status for get request is valid$")
	public void the_status_for_get_request_is_valid() {
	    reqRest.getResponseCode();
	}
	
	@Given("^user list all users$")
	public void user_list_all_users()  {
	   List<ReqResUserModel> users = reqRest.listAllUsers().getData();
	   context.saveData("users", users);
	   
	}

	@When("^user send a post request to create \"([^\"]*)\"$")
	public void user_send_a_post_request_to_create(String name)  {
	    reqRest.postRequestRestApi(name);
	}

	@Then("^chek the status for post request is valid$")
	public void chek_the_status_for_post_request_is_valid() throws Throwable {
	    reqRest.postResponseCode();
	}
	
	@When("^user check if \"([^\"]*)\" as user exist and collect user ID$")
	public void user_check_if_as_user_exist_and_collect_user_ID(String name)  {
	    reqRest.chekUserAndGetId(name);
	    
	}

	@Then("^user can be updated to \"([^\"]*)\"$")
	public void user_can_be_updated_to(String name) throws Throwable {
		reqRest.putRequestRestApiById(name);
	}
	
	@Then("^user can be deleted$")
	public void user_can_be_deleted()  {
	    reqRest.deleteUserById();
	}
	@Given("^user send a get request and status is valid$")
	public void user_send_a_get_request_and_status_is_valid() {
	    reqRest.getReqWithVerify();
	}


}
