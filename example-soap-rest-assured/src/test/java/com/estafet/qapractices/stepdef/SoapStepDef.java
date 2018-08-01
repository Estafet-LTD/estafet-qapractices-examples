/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.stepdef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.estafet.qapractices.request.CreateRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

/*
 * Created by Anton Rusanov on 31/05/2018
 */
/*
 * This is our step definition class for the cucumber steps.
 */
public class SoapStepDef {

    CreateRequest request = new CreateRequest();
    private Response response;
    // This XmlPath will help us to convert the required data from the xml response
    // and parse it to a String.
    private XmlPath xmlPath;

    @Given("^a request with bank code (.*) is send$")
    public void sendingRequestWithBankCode(String code) {
        request.setBankCode(code);
        // There 2 important thing that we have in our request:
        // 1. In content type - should be text/xml format.
        // 2. We have to set up the headers- like in the code below. This will ensure
        // that the request will be send in Soap format.
        response = RestAssured.given().request().contentType("text/xml; charset=UTF-8;").body(request.message())
                .header("SOAPAction", "findSoapAction", "Content-Type", "text/xml").when().post();

        assertTrue(response.getStatusCode() == 200);
    }

    @Then("^bank name in response xml should be (.*)$")
    public void verifyBankName(String bankName) {

        request.setBody(response.prettyPrint());

        this.xmlPath = response.xmlPath();
        request.setXmlNode(xmlPath.getString("ns1:bezeichnung"));

        String bank = request.getXmlNode().split(" ")[0];

        assertEquals(bankName, bank);

    }

}
