package com.estafet.qapractices.stepDefs;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.exceptions.TestException;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by Petar Petrov on 09-May-18.
 */
public class JsonStepDefs {

    private Context context;

    @Inject
    public JsonStepDefs(final Context context) {
        this.context = context;
    }

    @Given("^the user has a JSON$")
    public void userHasJson(final String firstJson) {
        context.saveData("firstJson", firstJson);
    }

    @Given("^the same JSON with out of order attributes$")
    public void sameJsonWithMissingAttr(final String secondJson) {
        context.saveData("secondJson", secondJson);
    }

    @When("^we compare the two JSONs$")
    public void weCompareTheTwoJsons() {
        try {
            JSONAssert.assertEquals(context.getSavedData("firstJson").toString(),
                        context.getSavedData("secondJson").toString(),
                        JSONCompareMode.LENIENT);
        } catch (JSONException e) {
            throw new TestException("Error parsing JSONs");
        }
    }
}
