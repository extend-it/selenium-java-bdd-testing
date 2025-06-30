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
        // Was driver.findElement(...).sendKeys(...): these three fields are
        // React-controlled inputs that don't pick up Selenium's native key
        // events (see BasePage.typeText() for the full story). Every field
        // came back empty afterwards no matter how the click/focus/keys
        // were sequenced.
        typeText(FIRST_NAME_INPUT, firstName);
        typeText(LAST_NAME_INPUT, lastName);
        typeText(POSTAL_CODE_INPUT, postalCode);
    }

    public void continueToOverview() {
        click(CONTINUE_BUTTON);
    }

    public String getErrorMessage() {
        return waitForVisible(ERROR_MESSAGE).getText();
    }
}
