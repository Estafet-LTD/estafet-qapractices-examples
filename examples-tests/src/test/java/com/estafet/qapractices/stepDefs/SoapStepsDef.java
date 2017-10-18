package com.estafet.qapractices.stepDefs;


import com.estafet.qapractices.service.template.CitiesByCountryRequest;
import cucumber.api.java.en.Given;

/**
 * Created by Ludmila Nenkova on 16/10/17.
 */
public class SoapStepsDef {

    @Given("I get cities by (.*)$")
    public void getCities(String country){
        String requestXML = CitiesByCountryRequest.GET_CITIES_BY_COUNTRY_REQUEST_TEMPLATE;
        requestXML = String.format(requestXML,country);

        String soapResponse;
        //soapResponse

    }

}
