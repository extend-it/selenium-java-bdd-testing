package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static final By CART_ITEM_NAMES = By.cssSelector("[data-test='inventory-item-name']");
    private static final By CHECKOUT_BUTTON = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNames() {
        // Was driver.findElements(...) with no wait at all: called right
        // after opening the cart (or right after a remove-item click), it
        // could run before the list had re-rendered and see stale/partial
        // contents. waitForVisible() gives the first item time to appear.
        waitForVisible(CART_ITEM_NAMES);
        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(CART_ITEM_NAMES)) {
            names.add(el.getText());
        }
        return names;
    }

    public void removeItemByName(String itemName) {
        String slug = itemName.toLowerCase().replace(" ", "-");
        waitForClickable(By.cssSelector("[data-test='remove-" + slug + "']")).click();
    }

    public void checkout() {
        waitForClickable(CHECKOUT_BUTTON).click();
    }
}
