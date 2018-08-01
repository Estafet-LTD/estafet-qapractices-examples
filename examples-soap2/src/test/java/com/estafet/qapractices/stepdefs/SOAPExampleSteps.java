/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.stepdefs;

import com.estafet.qapractices.utils.FileReader;
import com.estafet.qapractices.utils.IConsts;
import com.estafet.qapractices.utils.SOAPConn;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by User on 16.11.2017 г..
 */
public class SOAPExampleSteps implements IConsts {
    private static Document tempDoc;
    private static SOAPMessage soapRequest;
    private static SOAPMessage soapResponse;

    @Given("^cities for (.*) are requested$")
    public void prepareCountry(String country) throws SOAPException {
        tempDoc = FileReader.readFile(GET_CITIES_BY_COUNTRY_SAMPLE_REQUEST, REQUEST_TEST_FILES_LOCATION);
        FileReader.editXMLElement(tempDoc, "CountryName", country);
    }

    @When("^request is sent$")
    public void sendRequest() throws SOAPException {
        soapRequest = SOAPConn.generateSOAPRequest(tempDoc);
        // Print the request message /
        System.out.println("Request SOAP Message = : ");
        try {
            SOAPConn.prettyXml(soapRequest.getSOAPPart().getContent());
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        soapRequest.getMimeHeaders().setHeader("SOAPAction", "\"http://www.webserviceX.NET/GetCitiesByCountry\"");
        soapRequest.saveChanges();

        soapResponse = SOAPConn.getResponse(soapRequest, SERVICE_URL);
    }

    @Then("verify list of cities (.*) is returned")
    public void checkReturnedCities(String cities) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Print all the cities returned in the response : ");

        String responseAsString = soapResponse.getSOAPPart().getElementsByTagName("GetCitiesByCountryResult").item(0).getTextContent();
        List<String> citiesReturned = new ArrayList();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(responseAsString));

        Document doc = builder.parse(src);
        NodeList newDataSet = doc.getElementsByTagName("NewDataSet");
        if (newDataSet != null && newDataSet.getLength() == 1) {
            NodeList newDataSetList = ((Element) newDataSet.item(0)).getElementsByTagName("Table");
            for (int i = 0; i < newDataSetList.getLength(); i++) {
                Element newDataSetNode = (Element) newDataSetList.item(i);
                citiesReturned.add(newDataSetNode.getElementsByTagName("City").item(0).getTextContent());
                System.out.println(citiesReturned.get(i));
            }
        }
        String[] expectedCitiesList = cities.split(", ");
        Assert.assertEquals(Arrays.asList(expectedCitiesList), citiesReturned);
    }

}
