/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.tests.core;

import com.google.inject.Inject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Unit 1 on 11-Nov-17.
 */

/**
 * This class contains the actual program logic of our steps. Step defs file shouldn't contain too much
 * programmatic logic and the Tasks class shouldn't contain high level code.
 * This class is used for using the simple operations from Tasks and using them to provide simple output for the step defs file.
 * Think of it this way:
 * 1. Don't use the webdriver instance directly. Create a public method in Tasks
 * 2. Use this public method here in methods which produce String, booleans, int (or collections of those)
 * 3. Use the results of the methods here for assertions and iterations in the step defs classes
 */
public class WikipediaTasks {

    private Tasks tasks;
    private Properties properties;
    private Context context;

    @Inject
    public WikipediaTasks(final Tasks tasks, final Context context) throws IOException {
        this.tasks = tasks;
        this.properties = tasks.getProperties();
        this.context = context;
    }

    public void openWikipediaHomePage() {
        tasks.openPage(properties.getProperty("wikipediaUrl"));
    }

    public void searchFor(final String searchString) {
        tasks.findElement(WikipediaConstants.searchBar).sendKeys(searchString, Keys.RETURN);
        tasks.waitForArticleToLoad(searchString);
    }

    public void openLink(final String link) {
        final List<WebElement> links = tasks.findElements(WikipediaConstants.wikipediaLinks);
        for (final WebElement element : links) {
            if (element.getAttribute("title").equals(link)) {
                element.click();
                tasks.waitForArticleToLoad(link);
                break;
            }
        }
    }

    public boolean isOnPage(final String page) {
        final String currentPage = tasks.getCurrentPage();
        final String expectedPage = properties.get("wikipediaEnglish")
                + page.replace(" ", "_");
        return currentPage.equals(expectedPage);
    }

    public void openHistory() {
        tasks.findElement(WikipediaConstants.wikipediaHistory).click();
        tasks.waitForPageToLoad("action=history");
    }

    public void printRevisions(final int numberOfRevisions) {
        final List<WebElement> revisions = tasks.findElements(WikipediaConstants.wikipediaRevisions);

        if (revisions.size() < numberOfRevisions) {
            throw new TestException("Number of actual revisions is lower than the desired number. "
                    + "Expected: " + numberOfRevisions + "\nActual: " + revisions.size());
        }

        for (final WebElement element : revisions.subList(0, numberOfRevisions)) {
            // getScenario().write(message) prints a given string. This is the preferred method.
            // System.out.print is not recommended.
            context.writeOut(element.getText());
        }
    }


}
