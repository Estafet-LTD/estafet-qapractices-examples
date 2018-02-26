package com.estafet.qapractices.tests.stepDefs;

import com.estafet.qapractices.tests.core.WikipediaTasks;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by Unit 1 on 11-Nov-17.
 */
public class WikipediaStepDefs {

    private WikipediaTasks wikipediaTasks;

    @Inject
    public WikipediaStepDefs(final WikipediaTasks wikipediaTasks) {
        this.wikipediaTasks = wikipediaTasks;
    }

    @Given("^the user is on the home page$")
    public void userOpensHomePage() {
        wikipediaTasks.openWikipediaHomePage();
    }

    @Given("^the user searches Wikipedia for \"([^\"]*)\"$")
    public void userSearchesWikipedia(final String searchString) {
        wikipediaTasks.searchFor(searchString);
        Assert.assertTrue("Current page does not match expected one.", wikipediaTasks.isOnPage(searchString));
    }

    @Given("^the user opens the link for \"([^\"]*)\"$")
    public void userOpensTheLink(final String openLink) {
        wikipediaTasks.openLink(openLink);
    }

    @When("^the user is redirected to the \"([^\"]*)\" article$")
    public void userIsRedirected(final String link) {
        Assert.assertTrue("Current page does not match expected one.", wikipediaTasks.isOnPage(link));
    }

    @When("^the user opens the history menu")
    public void userOpensPage() {
        wikipediaTasks.openHistory();
    }

    @Then("^the user can print the last \"([^\"]*)\" revision dates$")
    public void userPrintsLastRevisions(final int numberOfRevisions) {
        wikipediaTasks.printRevisions(numberOfRevisions);
    }
}
