package com.estafet.qapractices.service.utils;

import com.eviware.soapui.impl.wsdl*;
/**
 * Created by Ludmila Nenkova on 16/10/17.
 */
public class SOAPUtils {

    public static String createRequestAndResponse(String endPointUrl, String requestXML, String operationName){
        String soapResponse = "";

        try{
            WsdlProject project = new WsdlProject();
            WSdlInterface[] wsdls = WsdlImporter.importWsdl(project,endPointUrl);
            WSdlInterface wsdl = wsdls[0];

            for (Operation operation : wsdl.getOperationList()){

            }
        }

        return soapResponse;

    }


}
