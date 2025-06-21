package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.CartPage;
import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.util.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CartSteps {

    private final InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
    private final CartPage cartPage = new CartPage(DriverFactory.getDriver());

    @Then("the cart contains {string}")
    public void assertCartContains(String itemName) {
        Assertions.assertTrue(cartPage.getItemNames().contains(itemName));
    }

    @When("the user removes {string} from the cart")
    public void removeFromCart(String itemName) {
        cartPage.removeItemByName(itemName);
    }

    @Then("the cart does not contain {string}")
    public void assertCartDoesNotContain(String itemName) {
        Assertions.assertFalse(cartPage.getItemNames().contains(itemName));
    }

    @When("the user proceeds to checkout")
    public void proceedToCheckout() {
        cartPage.checkout();
    }
}
