package com.example.saucedemo.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {

    private static final Logger LOG = LogManager.getLogger(DriverFactory.class);

    // Was a plain "private static WebDriver driver" field. That was fine
    // while junit-platform.properties ran everything on one thread, but the
    // moment cucumber.execution.parallel.enabled=true went live (see
    // junit-platform.properties), two scenarios on two threads raced on
    // this field: thread A's @Before would see thread B's half-quit driver,
    // or both threads would drive the *same* browser tab simultaneously.
    // ThreadLocal gives each execution thread its own WebDriver instance.
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(createDriver());
        }
        return DRIVER.get();
    }

    private static WebDriver createDriver() {
        String browser = ConfigReader.get("browser", "chrome");
        LOG.info("starting {} driver", browser);
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(ConfigReader.get("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1400,1000");
        return new ChromeDriver(options);
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
