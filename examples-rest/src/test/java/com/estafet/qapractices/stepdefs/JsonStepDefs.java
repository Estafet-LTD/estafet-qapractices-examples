/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.stepdefs;

import com.estafet.qapractices.context.Context;
import com.estafet.qapractices.exceptions.TestException;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;

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
        /*
          We first simplify the JSON objects to remove parameters we don't care about
         */
        final String actual = simplify(context.getSavedData("firstJson").toString());
        final String expectedJson = simplify(context.getSavedData("secondJson").toString());
        /*
          This is the actual JSONAssert. It uses LENIENT mode-
          doesn't care for the order of attributes
          and doesn't care if the second JSON (actual) has extra parameters.
          There are more modes which you can find here-
          http://jsonassert.skyscreamer.org/javadoc/org/skyscreamer/jsonassert/JSONCompareMode.html
         */
        try {
            JSONAssert.assertEquals(actual, expectedJson, JSONCompareMode.LENIENT);
        } catch (JSONException e) {
            throw new TestException("JSONs do not match");
        }
    }

    /**
     * This method removes unwanted JSON attributes and returns a String with the simplified JSON
     */
    private String simplify(final String originalJson) {
        /*
            This list will contain the attributes we want to remove.
         */
        final List<String> exclude = new ArrayList<>();
        exclude.add("unwantedParameterInRoot");
        exclude.add("additionalUnwantedParameter");
        final JSONParser parser = new JSONParser();
        JSONObject json;
        try {
            /*
                We parse the original JSON to a JSONObject
             */
            json = (JSONObject) parser.parse(originalJson);
            /*
                This method removes the attributes from the main level
             */
            json.keySet().removeAll(exclude);
            /*
                There are attributes we want to remove from inside of a JSON array.
                So we parse the first (and only) element of the array and remove it's attributes.
                Keep in ming: removeAll removes attributes only on the main level of the JSON
             */
            final JSONArray nodesObject = (JSONArray) json.get("nodes");
            final JSONObject nodeObject = (JSONObject) nodesObject.get(0);
            nodeObject.keySet().removeAll(exclude);
        } catch (ParseException e) {
            throw new TestException("Error parsing string to JSON");
        }
        return json.toJSONString();
    }
}
