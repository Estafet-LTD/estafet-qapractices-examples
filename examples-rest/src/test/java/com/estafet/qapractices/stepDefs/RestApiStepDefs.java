package com.estafet.qapractices.stepDefs;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.models.HttpBinModel;
import com.estafet.qapractices.models.ReqResUserModel;
import com.estafet.qapractices.rest.HttpBinTasks;
import com.estafet.qapractices.rest.ReqResTasks;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by Pesho on 15-Sep-17.
 */
public final class RestApiStepDefs {

    private HttpBinTasks httpBinTasks;
    private Context context;
    private ReqResTasks reqResTasks;

    @Inject
    public RestApiStepDefs(final HttpBinTasks httpBinTasks, final Context context, final ReqResTasks reqResTasks) {
        this.httpBinTasks = httpBinTasks;
        this.context = context;
        this.reqResTasks = reqResTasks;
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

    @Given("^a user lists all available users$")
    public void userListsAllUsers() {
        List<ReqResUserModel> users = reqResTasks.listUsers().getData();
        context.saveData("users" , users);

    }

    @When("^user \"([^\"]*)\" (doesn't )?exist$")
    public void userCannotFindAmongExisting(final String userName, final String negate) {
        List<ReqResUserModel> users = (List<ReqResUserModel>) context.getSavedData("users");
        for (ReqResUserModel user : users) {
            if (negate != null) {
                Assert.assertNotEquals("User already exists!", user.getFirstName(), userName.split(" ")[0]);
                Assert.assertNotEquals("User already exists!", user.getLastName(), userName.split(" ")[1]);
            } else if (user.getFirstName().equals(userName.split(" ")[0])
                    && user.getLastName().equals(userName.split(" ")[1])){
                        context.saveData("userIndex", user.getId());
            }
        }
        if (negate == null) {
            Assert.assertNotNull("User does not exist.", context.getSavedData("userIndex"));
        }
    }

    @Then("^the user create a new user \"([^\"]*)\"$")
    public void userCreatesNewUser(final String userNames) {
        final String[] splitUserNames = userNames.split(" ");
        ReqResUserModel userModel = new ReqResUserModel();
        userModel.setFirstName(splitUserNames[0]);
        userModel.setLastName(splitUserNames[1]);
        userModel.setAvatar("dickpic");

        final Response createResp = reqResTasks.createUser(userModel);
        Assert.assertEquals("Response code is not 201", createResp.getStatus(), 201);
    }

    @Then("the user can be updated to \"([^\"]*)\"$")
    public void canBeUpdate(final String userNameTobeUsed) {
        final String userId  = (String) context.getSavedData("userIndex");

        ReqResUserModel userModel = new ReqResUserModel();
        userModel.setFirstName(userNameTobeUsed.split(" ")[0]);
        userModel.setLastName(userNameTobeUsed.split(" ")[1]);

        final Response response = reqResTasks.updateUser(userId, userModel);
        System.out.println("pe6o " + response.readEntity(String.class));
    }
}
