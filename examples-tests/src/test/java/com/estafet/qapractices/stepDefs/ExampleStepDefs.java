package com.estafet.qapractices.stepDefs;

import com.estafet.qapractices.sampleClasses.ExampleClass;
import cucumber.api.java.en.Given;

/**
 * Created by Pesho on 01-Sep-17.
 */
public final class ExampleStepDefs {

    @Given("^an example step$")
    public void anExampleStep() {
        ExampleClass exampleClass = new ExampleClass();
        exampleClass.setExampleVariable("It works!!!");
        System.out.println(exampleClass.getExampleVariable());
    }
}
