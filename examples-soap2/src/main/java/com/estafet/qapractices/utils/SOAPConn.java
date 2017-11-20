package com.estafet.qapractices.utils;

import org.w3c.dom.Document;

import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by User on 16.11.2017 г..
 */
public class SOAPConn {
    public static MessageFactory messageFactory;
    private static SOAPConnectionFactory soapConnectionFactory;
    private static SOAPConnection soapConnection;
    private static SOAPMessage soapResponse;

    static {
        try {
            messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public static void createConn() {
        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
            System.out.println("\nCreating SOAP Connection .......");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public static void closeSOAPConn() {
        try {
            soapConnection.close();
            System.out.println("\nClosing SOAP Connection......");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public static void prettyXml(Source message) {
        try {
            StreamResult result = new StreamResult(new StringWriter());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            transformer.transform(message, result);
            System.out.println(result.getWriter().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SOAPMessage getResponse(SOAPMessage msg, String serviceURL) {
        try {
            soapResponse = soapConnection.call(msg, serviceURL);
            printSOAPResponse(soapResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soapResponse;
    }

    public static void printSOAPResponse(SOAPMessage soapResponse) {
        try {
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            System.out.println("\nResponse SOAP Message = ");
            prettyXml(sourceContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SOAPMessage generateSOAPRequest(Document doc) {
        SOAPMessage soapMessage = null;
        try {
            soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            Source source = new DOMSource(doc);
            soapPart.setContent(source);
            soapMessage.saveChanges();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return soapMessage;
    }

}
