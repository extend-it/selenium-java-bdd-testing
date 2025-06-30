package com.example.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import com.example.saucedemo.util.ConfigReader;

public abstract class BasePage {

    protected static final Logger LOG = LogManager.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        int timeoutSeconds = ConfigReader.getInt("explicit.wait.seconds", 10);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for the element to be clickable, then clicks it via JS instead
     * of a real pointer click. Confirmed against the live site: the
     * shopping-cart-link element has no href (its navigation is a React
     * onClick handler, not a real link), and Selenium's native click - a
     * synthetic pointer event at the element's calculated center - was
     * intermittently swallowed in headless Chrome without triggering that
     * handler, leaving the page on inventory.html. element.click() via
     * JavascriptExecutor calls the DOM click() method directly and doesn't
     * have that failure mode.
     */
    protected void click(By locator) {
        WebElement element = waitForClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
