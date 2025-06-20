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
        // BUG: copy-pasted from the "add to cart" step below by mistake -
        // this clicks the *add* button (which, on an item already in the
        // cart, does nothing useful) instead of the cart's remove button.
        inventoryPage.addItemToCartByName(itemName);
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
