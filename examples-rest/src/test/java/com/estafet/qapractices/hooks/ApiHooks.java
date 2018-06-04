package com.estafet.qapractices.hooks;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.rest.ReqResTasks;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by Petar Petrov on 24-Apr-18.
 */

/**
 * This is a hooks class for the ReST examples
 */
public class ApiHooks {
	
    private Context context;
	private ReqResTasks reqResTask;

    @Inject
    public ApiHooks(final Context context, final ReqResTasks reqResTasks) {
        this.context = context;
        this.reqResTask = reqResTasks;
    }

    @Before
    public void setScenario(final Scenario scenario) {
        this.context.setScenario(scenario);
    }
    @After("@Rest2")
    public void deleteUser() {
    	
    	String userId = (String) context.getSavedData("new userId");
    	
    	final Response response = reqResTask.deleteUser(userId);
    	System.out.println("Delete status is:" + response.getStatus());
    	assertTrue(response.getStatus()== 204);

    }
}
