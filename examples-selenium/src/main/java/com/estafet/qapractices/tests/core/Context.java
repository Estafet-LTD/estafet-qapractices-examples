/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.tests.core;

import com.google.common.collect.Maps;
import cucumber.api.Scenario;
import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.Map;

/**
 * Created by Pesho on 15-Sep-17.
 */

@ScenarioScoped
public class Context {

    private Map<String, Object> dataMap = Maps.newHashMap();
    private Scenario scenario;

    public void saveData(final String identifier, final Object data) {
        this.dataMap.put(identifier, data);
    }

    public Object getSavedData(final String identifier) {
        return dataMap.get(identifier);
    }

    public void setScenario(final Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void writeOut(final String message) {
        getScenario().write(message);
    }
}
