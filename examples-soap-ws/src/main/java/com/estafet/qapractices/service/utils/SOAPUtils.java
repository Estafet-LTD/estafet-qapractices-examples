/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.service.utils;

import com.estafet.qapractices.entities.CountryAndCity;
import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.wsdl.*;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.settings.HttpSettings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ludmila Nenkova on 16/10/17.
 */
public class SOAPUtils {

    private SOAPUtils () {

    }

    public static String createRequestAndResponse(String endPointUrl, String requestXML, String operationName) {
        String soapResponse = "";
        try {
            WsdlProject project = new WsdlProject();
            WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, endPointUrl);
            WsdlInterface wsdl = wsdls[0];
            SoapUI.getSettings().setBoolean(HttpSettings.RESPONSE_COMPRESSION, false);
            for (Operation operation : wsdl.getOperationList()) {
                WsdlOperation op = (WsdlOperation) operation;
                if (op.getName().equalsIgnoreCase(operationName)) {
                    WsdlRequest wsdlRequest = op.addNewRequest("My Request");

                    wsdlRequest.setTimeout(String.valueOf(TimeUnit.HOURS.toMillis(3L)));
                    wsdlRequest.setRequestContent(requestXML);
                    WsdlSubmitContext wsdlSubmitContext = new WsdlSubmitContext(wsdlRequest);
                    WsdlSubmit<?> submit = wsdlRequest.submit(wsdlSubmitContext, false);
                    Response response = submit.getResponse();
                    soapResponse = response.getContentAsString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapResponse;
    }

    public static List<CountryAndCity> parseXMLString(String response) throws ParserConfigurationException, IOException, SAXException {

        List<CountryAndCity> citiesListFromXML = new ArrayList<CountryAndCity>();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(response));

        Document doc = builder.parse(src);
        NodeList newDataSet = doc.getElementsByTagName("NewDataSet");
        if ( newDataSet != null && newDataSet.getLength() == 1) {

            NodeList newDataSetList = ((Element)newDataSet.item(0)).getElementsByTagName("Table");

            int referenceCount = newDataSetList.getLength();

            for (int i = 0; i < referenceCount; i++) {

                Element newDataSetNode =  (Element) newDataSetList.item(i);
                CountryAndCity countryAndCity = new CountryAndCity();
                countryAndCity.setCity(newDataSetNode.getElementsByTagName("City").item(0).getTextContent());
                countryAndCity.setCountry(newDataSetNode.getElementsByTagName("Country").item(0).getTextContent());

                citiesListFromXML.add(countryAndCity);
            }
        }
        return citiesListFromXML;

    }

}
