package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.CheckoutCompletePage;
import com.example.saucedemo.pages.CheckoutInfoPage;
import com.example.saucedemo.pages.CheckoutOverviewPage;
import com.example.saucedemo.util.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CheckoutSteps {

    private final CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(DriverFactory.getDriver());
    private final CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverFactory.getDriver());
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverFactory.getDriver());

    @When("the user fills in checkout information {string}, {string}, {string}")
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutInfoPage.fillInfo(firstName, lastName, postalCode);
        checkoutInfoPage.continueToOverview();
    }

    @Then("the order total equals the subtotal plus tax")
    public void assertOrderTotal() {
        double subtotal = checkoutOverviewPage.getSubtotal();
        double tax = checkoutOverviewPage.getTax();
        double total = checkoutOverviewPage.getTotal();
        Assertions.assertEquals(subtotal + tax, total, 0.01);
    }

    @When("the user finishes the order")
    public void finishOrder() {
        checkoutOverviewPage.finish();
    }

    @Then("the order confirmation {string} is shown")
    public void assertOrderConfirmation(String expectedText) {
        Assertions.assertEquals(expectedText, checkoutCompletePage.getConfirmationHeader());
    }

    @Then("the checkout info error {string} is shown")
    public void assertCheckoutInfoError(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, checkoutInfoPage.getErrorMessage());
    }
}
