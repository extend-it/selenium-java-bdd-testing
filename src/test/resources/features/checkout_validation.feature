Feature: Checkout validation

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"
    And the user adds "Sauce Labs Backpack" to the cart
    And the user opens the cart
    And the user proceeds to checkout

  @regression @negative
  Scenario: Missing first name is rejected
    When the user fills in checkout information "", "Doe", "12345"
    Then the checkout info error "Error: First Name is required" is shown
