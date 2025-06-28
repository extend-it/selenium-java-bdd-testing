package com.example.saucedemo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.example.saucedemo.util.DriverFactory;

public class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        LOG.info("starting scenario: {}", scenario.getName());
        DriverFactory.getDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOG.info("finished scenario: {} status={}", scenario.getName(), scenario.getStatus());
        // Screenshot happens before quitDriver() now - the driver has to
        // still be alive to take one.
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            LOG.warn("scenario failed, screenshot attached: {}", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
