package com.example.saucedemo.stepdefinitions;

import com.example.saucedemo.pages.LoginPage;
import com.example.saucedemo.util.ConfigReader;
import com.example.saucedemo.util.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("the Swag Labs login page is open")
    public void openLoginPage() {
        loginPage.open(ConfigReader.get("base.url", "https://www.saucedemo.com/"));
    }

    @When("the user logs in as {string} with password {string}")
    public void loginAs(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user lands on the inventory page")
    public void assertInventoryPageShown() {
        Assertions.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory.html"));
    }

    @Then("the login error {string} is shown")
    public void assertLoginError(String expectedMessage) {
        // BUG: SauceDemo's error text is normal sentence case
        // ("Epic sadface: ..."), so lower-casing the expected value before
        // comparing means this can never match the real (non-lowercase) text.
        Assertions.assertTrue(loginPage.getErrorMessage().contains(expectedMessage.toLowerCase()));
    }
}
