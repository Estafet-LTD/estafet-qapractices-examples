package com.estafet.qapractices.context;

import com.google.common.collect.Maps;
import cucumber.api.Scenario;
import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.Map;

/**
 * Created by Pesho on 15-Sep-17.
 */

/**
 * This class holds information that needs to be shared across the entire test.
 * Notice the @ScenarioScoped annotation in the beginning. This means that the lifecycle of this class is the current
 * scenario.
 * DO NOT INSTANTIATE THIS CLASS! It is only to be injected.
 * It will be expanded in the future. Do not hesitate to inject the class anywhere in the code.
 */
@ScenarioScoped
public class Context {

    /**
     * dataMap's purpose is to save any kind of data to be reused anywhere in the code.
     */
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

    /**
     *  Writes a string to the default output (console). Use this instead of println()
     */
    public void writeOut(final String message) {
        this.scenario.write(message);
    }


}
