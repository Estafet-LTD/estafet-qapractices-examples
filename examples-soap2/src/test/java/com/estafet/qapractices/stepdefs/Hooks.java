/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.stepdefs;

import com.estafet.qapractices.utils.SOAPConn;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by User on 20.11.2017 г..
 */
public class Hooks {

    @Before
    public void setUp() {
        SOAPConn.createConn();
    }

    @After
    public void tearDown() {
        SOAPConn.closeSOAPConn();
    }
}
