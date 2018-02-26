package com.estafet.qapractices.tests.testhooks;

import com.estafet.qapractices.tests.core.Context;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.runtime.java.guice.ScenarioScoped;

/**
 * Created by Unit 1 on 07-Jul-17.
 */
@ScenarioScoped
public class UiHooks {

    private Context context;

    @Inject
    public UiHooks(final Context context) {
        this.context = context;
    }

    @Before
    public void before(Scenario scenario) {
        context.setScenario(scenario);
    }
}
