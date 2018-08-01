/**
 * Copyright (C) Estafet Ltd
 */
package com.estafet.qapractices.tests.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by Petar Petrov on 26-Feb-18.
 */

/**
 * Declaring this class as singleton is very important. If the annotation is missing
 * a new WebDriver instance will be opened for each step which is mildly amusing but not very practical
 *
 * And this is where the magic happens...
 */
@Singleton
public class Tasks {

    public WebDriver driver;
    public static int webDriverWaitInSeconds = 5;
    public Properties properties;

    @Inject
    public Tasks() throws IOException {
        this.driver = new MarionetteDriver().getFirefoxDriver();
        // Loading the properties file. Note that this is different from the yml file used in the rest module.
        // It's more simple and has less functionality but this is the better approach if you only need a place to
        // dump strings for single environment.
        this.properties = new Properties();
        final InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/conf/config.properties");
        this.properties.load(inputStream);
    }

    // Opens a page
    public void openPage(final String url) {
        driver.get(url);
    }

    // This is a thing of beauty- my patent:
    // this method finds an element by a CSS selector, waits up to webDriverWaitInSeconds for it to be clickable
    // uses JS to scroll to it (to avoid those pesky bottom of the page elements) and handles exceptions.
    public WebElement findElement(final String identifier) {
        WebDriverWait wait = new WebDriverWait(driver, webDriverWaitInSeconds);
        WebElement element = driver.findElement(By.cssSelector(identifier));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return element;
    }

    // Finds multiple elements.
    public List<WebElement> findElements(final String identifier) {
        List<WebElement> elements = driver.findElements(By.cssSelector(identifier));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return elements;
    }

    // Explicit wait. Use this method if you are too lazy for handling ElementNotFound exception
    public void explicitWait(final int secondsToWait) {
        try {
            Thread.sleep(secondsToWait * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Waits for article to be loaded (URL with some string manipulations).
    public void waitForArticleToLoad(final String page) {
        new WebDriverWait(driver, webDriverWaitInSeconds).until(
                ExpectedConditions.urlToBe(properties.get("wikipediaEnglish")
                        + page.replace(" ", "_")));
    }

    // Waits for page URL to contain some string. This is the better way to wait between page transitions
    // CASE SENSITIVE
    public void waitForPageToLoad(final String page) {
        new WebDriverWait(driver, webDriverWaitInSeconds).until(
                ExpectedConditions.urlContains(page));
    }

    // Returns the current page URL
    public String getCurrentPage() {
        return driver.getCurrentUrl();
    }

    // Returns the properties already loaded in the constructor
    public Properties getProperties() {
        return this.properties;
    }

    // Closes the WebDriver instance
    public void closeBrowser() {
        driver.close();
    }
}
