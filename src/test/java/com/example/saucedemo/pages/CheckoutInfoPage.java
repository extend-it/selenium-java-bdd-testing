package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {

    private static final By FIRST_NAME_INPUT = By.cssSelector("[data-test='firstName']");
    private static final By LAST_NAME_INPUT = By.cssSelector("[data-test='lastName']");
    private static final By POSTAL_CODE_INPUT = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.cssSelector("[data-test='continue']");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillInfo(String firstName, String lastName, String postalCode) {
        // Was driver.findElement(FIRST_NAME_INPUT) with no wait: this page
        // is only reached by clicking "checkout" on the cart page, and the
        // client-side navigation to checkout-step-one.html isn't instant.
        // waitForVisible() gives it time to actually arrive first.
        waitForVisible(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(POSTAL_CODE_INPUT).sendKeys(postalCode);
    }

    public void continueToOverview() {
        waitForClickable(CONTINUE_BUTTON).click();
    }

    public String getErrorMessage() {
        return waitForVisible(ERROR_MESSAGE).getText();
    }
}
