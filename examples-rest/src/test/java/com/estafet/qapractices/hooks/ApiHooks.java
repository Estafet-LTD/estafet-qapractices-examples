package com.estafet.qapractices.hooks;

import com.estafet.qapractices.context.Context;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

/**
 * Created by Petar Petrov on 24-Apr-18.
 */

/**
 * This is a hooks class for the ReST examples
 */
public class ApiHooks {

    private Context context;

    @Inject
    public ApiHooks(final Context context) {
        this.context = context;
    }

    @Before
    public void setScenario(final Scenario scenario) {
        this.context.setScenario(scenario);
    }
}
