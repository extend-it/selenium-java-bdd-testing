package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {

    private static final By PAGE_TITLE = By.cssSelector("[data-test='title']");
    private static final By INVENTORY_ITEMS = By.cssSelector("[data-test='inventory-item']");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    private static final By ITEM_NAME = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_PRICE = By.cssSelector("[data-test='inventory-item-price']");
    private static final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");

    // BUG: cached after the first read. Sorting re-renders the whole list
    // (React), which detaches these WebElement references from the DOM;
    // reusing them afterwards throws StaleElementReferenceException.
    private java.util.List<WebElement> cachedItems;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return waitForVisible(PAGE_TITLE).getText();
    }

    public List<WebElement> getItems() {
        if (cachedItems == null) {
            waitForVisible(INVENTORY_ITEMS);
            cachedItems = driver.findElements(INVENTORY_ITEMS);
        }
        return cachedItems;
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

    public void openCart() {
        driver.findElement(CART_LINK).click();
    }

    public void sortBy(String optionValue) {
        new org.openqa.selenium.support.ui.Select(waitForVisible(SORT_DROPDOWN)).selectByValue(optionValue);
    }

    public List<String> getItemNamesInOrder() {
        List<String> names = new java.util.ArrayList<>();
        for (WebElement item : getItems()) {
            names.add(item.findElement(ITEM_NAME).getText());
        }
        return names;
    }

    public List<String> getItemPricesInOrder() {
        List<String> prices = new java.util.ArrayList<>();
        for (WebElement item : getItems()) {
            prices.add(item.findElement(ITEM_PRICE).getText());
        }
        return prices;
    }
}
