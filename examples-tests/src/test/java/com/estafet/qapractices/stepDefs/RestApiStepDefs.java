package com.estafet.qapractices.stepDefs;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.models.HttpBinModel;
import com.estafet.qapractices.rest.HttpBinTasks;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by Pesho on 15-Sep-17.
 */
public final class RestApiStepDefs {

    private HttpBinTasks httpBinTasks;
    private Context context;

    @Inject
    public RestApiStepDefs(final HttpBinTasks httpBinTasks, final Context context) {
        this.httpBinTasks = httpBinTasks;
        this.context = context;
    }

    @Given("^an user sends a GET request$")
    public void anUserSendsARequest() {
        final Response response = httpBinTasks.getMethodResponse();
        context.saveData("response", response);

    }

    @Given("^the response fot method \"([^\"]*)\" is valid$")
    public void theResponseIsValid(final String method) {
        final Response response = (Response) context.getSavedData("response");
        final String responseString = response.readEntity(String.class);
        final String SITE_URL = "http://httpbin.org/";
        //TODO We should avoid using println in the code but this is an example so we'll allow it.
        System.out.println("Response:\n" + responseString);
        Assert.assertEquals("Response code does not match", 200, response.getStatus());
        Assert.assertTrue("Response does not contain the expected URL",
                responseString.contains(SITE_URL + method));
    }

    @Given("^an user sends a POST request with the following parameters$")
    public void anUserSendsApostRequest(final DataTable data) {
        final Map<String, String> requestData = Maps.newHashMap(data.asMap(String.class, String.class));
        HttpBinModel model = new HttpBinModel();
        model.setUserName(requestData.get("User name"));
        model.setLength(requestData.get("Length"));
        model.setCupSize(requestData.get("Cup size"));
        model.setMeasures(requestData.get("Measures"));

        final Response response = httpBinTasks.postMethodResponse(model);
        context.saveData("response", response);
    }

}
