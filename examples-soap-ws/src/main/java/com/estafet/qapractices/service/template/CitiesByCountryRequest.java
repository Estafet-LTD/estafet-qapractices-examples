/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.service.template;

/**
 * Created by Ludmila Nenkova on 16/10/17.
 */
public class CitiesByCountryRequest {

    private CitiesByCountryRequest () {

    }

    public final static String GET_CITIES_BY_COUNTRY_REQUEST_TEMPLATE
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.webserviceX.NET\">\n"
            + "   <soapenv:Header/>\n"
            + "   <soapenv:Body>\n"
            + "      <web:GetCitiesByCountry>\n"
            + "         <web:CountryName>%s</web:CountryName>\n"
            + "      </web:GetCitiesByCountry>\n"
            + "   </soapenv:Body>\n"
            + "</soapenv:Envelope>";

}
