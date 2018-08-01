/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.hooks;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
/*
 * Created by Anton Rusanov on 05.06.2018
 */
public class ApiHooks {

    private static final String BASEURI = "http://www.thomas-bayer.com";
    private static final String BASEPATH = "/axis2/services/BLZService?wsdl";

    //This hook is to set up the url with wsdl path.
        @Before("@soap")
        public void setUp() {
            RestAssured.baseURI = BASEURI;
            RestAssured.basePath = BASEPATH;

        }

}
