package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {

    private static final By PAGE_TITLE = By.cssSelector("[data-test='title']");
    private static final By INVENTORY_ITEMS = By.cssSelector("[data-test='inventory-item']");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

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

    public void addItemToCartByName(String itemName) {
        String slug = itemName.toLowerCase().replace(" ", "-");
        driver.findElement(By.cssSelector("[data-test='add-to-cart-" + slug + "']")).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(CART_BADGE);
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.get(0).getText().trim());
    }
}
