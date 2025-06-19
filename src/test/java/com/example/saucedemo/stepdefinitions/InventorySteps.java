package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.util.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class InventorySteps {

    private final InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());

    @Then("all {int} products are listed")
    public void assertProductCount(int expectedCount) {
        Assertions.assertEquals(expectedCount, inventoryPage.getItems().size());
    }

    @When("the user adds {string} to the cart")
    public void addItemToCart(String itemName) {
        inventoryPage.addItemToCartByName(itemName);
    }

    @Then("the cart badge shows {int}")
    public void assertCartBadge(int expectedCount) {
        Assertions.assertEquals(expectedCount, inventoryPage.getCartBadgeCount());
    }
}
