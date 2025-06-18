package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.util.DriverFactory;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class InventorySteps {

    private final InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());

    @Then("all {int} products are listed")
    public void assertProductCount(int expectedCount) {
        Assertions.assertEquals(expectedCount, inventoryPage.getItems().size());
    }
}
