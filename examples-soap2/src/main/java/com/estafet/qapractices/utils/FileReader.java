package com.estafet.qapractices.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by User on 16.11.2017 г..
 */
public class FileReader {
    private static DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder docBuilder;
    private static Document doc = null;

    public static Document readFile(String fileName, String fileLocation) {
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File(fileLocation + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public static void editXMLElement(Document doc, String elem, String value) {
        try {
            doc.getElementsByTagName(elem).item(0).setTextContent(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
