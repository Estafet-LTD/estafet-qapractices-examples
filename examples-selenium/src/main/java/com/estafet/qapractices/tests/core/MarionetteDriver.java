package com.estafet.qapractices.tests.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Unit 1 on 26-Oct-17.
 */

/**
 * This class initializes the WebDriver.
 * In this project we use Selenium 3 with Geckodriver.
 * The current implementation is for Windows.
 * We should add the other two later.
 */
public class MarionetteDriver {

    public WebDriver getFirefoxDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        // This tells the driver is will use marionette (geckodriver)
        capabilities.setCapability("marionette", true);
        setGeckoDriver();
        final WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private void setGeckoDriver() {
        String pathToDriver = null;

        final String osName = System.getProperty("os.name").toLowerCase();

        //TODO Add Linux and the expensive one later.
        // If the user's OS is Windows we set up the geckodriver. Otherwisse there is a TestException thrown
        if (osName.toLowerCase().contains("windows")) {
            pathToDriver = System.getProperty("user.dir") + "/conf/geckodriver.exe";
        }
        if (pathToDriver == null) {
            throw new TestException("Gecko driver not found for OS: " + osName);
        }
        final File file = new File(pathToDriver);
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        // This option hides all the annoying Selenium loging. It can be turned off but I've never
        // found anything useful in the extra logging so I suggest leaving it this way.
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
    }
}
