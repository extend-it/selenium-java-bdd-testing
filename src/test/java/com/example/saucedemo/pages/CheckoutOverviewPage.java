package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private static final By SUBTOTAL_LABEL = By.cssSelector("[data-test='subtotal-label']");
    private static final By TAX_LABEL = By.cssSelector("[data-test='tax-label']");
    private static final By TOTAL_LABEL = By.cssSelector("[data-test='total-label']");
    private static final By FINISH_BUTTON = By.cssSelector("[data-test='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private double parseMoney(String label, String prefix) {
        return Double.parseDouble(label.replace(prefix, "").trim());
    }

    public double getSubtotal() {
        return parseMoney(waitForVisible(SUBTOTAL_LABEL).getText(), "Item total: $");
    }

    public double getTax() {
        return parseMoney(driver.findElement(TAX_LABEL).getText(), "Tax: $");
    }

    public double getTotal() {
        return parseMoney(driver.findElement(TOTAL_LABEL).getText(), "Total: $");
    }

    public void finish() {
        click(FINISH_BUTTON);
    }
}
