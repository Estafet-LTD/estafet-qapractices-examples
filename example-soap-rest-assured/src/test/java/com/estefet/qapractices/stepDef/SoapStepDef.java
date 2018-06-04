package com.estefet.qapractices.stepDef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.estefet.qapractices.request.CreateRequest;

import cucumber.api.java.Before;
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
	// This XmlPath will help us to convert the required data from the xml response and parse it to a String.
	private XmlPath xmlPath ;
	
	
	//This hook is to set up the url with wsdl path. 
	@Before("@soap1")
	public void setUp() {
		RestAssured.baseURI = "http://www.thomas-bayer.com";
		RestAssured.basePath = "/axis2/services/BLZService?wsdl";
		
	}
	
	@Given("^a request with bank code (.*) is send$")
	public void a_request_with_bank_code_is_send(String code)  {
	  request.setBankCode(code);
	  // There 2 important thing that we have in our request:
	  // 1. In content type - should be text/xml format.
	  // 2. We have to set up the headers- like in the code below. This will ensure that the request will be send in Soap format.
	  response = RestAssured.given()
			  .request()
			  .contentType("text/xml; charset=UTF-8;")
			  .body(request.message())
			  .header("SOAPAction", "findSoapAction", "Content-Type", "text/xml")
			  .when()
			  .post();
	  
	  assertTrue(response.getStatusCode()==200);
	}

	@Then("^bank name in response xml should be (.*)$")
	public void bank_name_in_response_xml_should_be(String bankName)  {
		
		request.body = response.prettyPrint();
		
		this.xmlPath = response.xmlPath();
		request.setXmlNode(xmlPath.getString("ns1:bezeichnung"));
		
		
	    String bank = request.getXmlNode().split(" ")[0];
	  
	   
	   assertEquals(bankName, bank);
		
	}

}
