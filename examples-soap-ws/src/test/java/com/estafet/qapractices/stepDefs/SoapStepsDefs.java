package com.estafet.qapractices.stepDefs;


import com.estafet.qapractices.configuration.Constants;
import com.estafet.qapractices.entities.CountryAndCity;
import com.estafet.qapractices.service.template.CitiesByCountryRequest;
import com.estafet.qapractices.service.utils.SOAPUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ludmila Nenkova on 16/10/17.
 */
public class SoapStepsDefs {

    public static String soapResponse;

    @When("I get cities by (.*)$")
    public void getCities(String country){
        String requestXML = CitiesByCountryRequest.GET_CITIES_BY_COUNTRY_REQUEST_TEMPLATE;
        requestXML = String.format(requestXML,country);

        String operationName = "GetCitiesByCountry";

        soapResponse = SOAPUtils.createRequestAndResponse(Constants.SOAP_SERVICE,requestXML,operationName);

    }

    @Then("I validate the response$")
    public void validatResponse() throws IOException, SAXException, ParserConfigurationException {
        List<CountryAndCity> countryAndCityListXMLResponse = SOAPUtils.parseXMLString(soapResponse);

        for (int i = 0; i < countryAndCityListXMLResponse.size(); i++){
            System.out.println(countryAndCityListXMLResponse.get(i));
        }

    }

}
