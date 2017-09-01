package com.estafet.qapractices.stepDefs;

import com.estafet.qapractices.SampleClasses.ExampleClass;
import cucumber.api.java.en.Given;

/**
 * Created by Pesho on 01-Sep-17.
 */
public class ExampleStepDefs {

    @Given("^an example step$")
    public void anExampleStep() {
        ExampleClass exampleClass = new ExampleClass();
        exampleClass.setExampleVariable("It works!!!");
        System.out.println(exampleClass.getExampleVariable());
    }
}
