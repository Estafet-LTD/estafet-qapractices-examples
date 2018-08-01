/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.request;
/*
 * Created by Anton Rusanov on 31/05/2018
 */
public class CreateRequest {

    private String bankCode;

    private String body;
    private String xmlNode;
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getXmlNode() {
        return xmlNode;
    }

    public void setXmlNode(String xmlNode) {
        this.xmlNode = xmlNode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    //This method will be in use for putting it to the Rest Assured body() method. This is our message request.
    public String message() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:blz=\"http://thomas-bayer.com/blz/\">"
          + "<soapenv:Header/>"
          + "<soapenv:Body>"
          + " <blz:getBank>"
                 + "<blz:blz>" + bankCode + "</blz:blz>"
             + "</blz:getBank>"
           + "</soapenv:Body>"
        + "</soapenv:Envelope>";
    }

}
