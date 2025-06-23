package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.util.DriverFactory;
import com.example.saucedemo.util.SortOptionMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @When("the user opens the cart")
    public void openCart() {
        inventoryPage.openCart();
    }

    @When("the user sorts products by {string}")
    public void sortProducts(String sortLabel) {
        inventoryPage.sortBy(SortOptionMapper.toOptionValue(sortLabel));
    }

    @Then("the products are listed by price low to high")
    public void assertSortedByPriceAscending() {
        List<String> priceTexts = inventoryPage.getItemPricesInOrder();
        List<Double> actual = priceTexts.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sorted = new ArrayList<>(actual);
        Collections.sort(sorted);
        Assertions.assertEquals(sorted, actual);
    }
}
