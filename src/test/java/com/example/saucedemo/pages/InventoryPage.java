package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {

    private static final By PAGE_TITLE = By.cssSelector("[data-test='title']");
    private static final By INVENTORY_ITEMS = By.cssSelector("[data-test='inventory-item']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return waitForVisible(PAGE_TITLE).getText();
    }

    public List<WebElement> getItems() {
        waitForVisible(INVENTORY_ITEMS);
        return driver.findElements(INVENTORY_ITEMS);
    }
}
