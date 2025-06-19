Feature: Add to cart

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"

  @smoke @regression
  Scenario: Add a single item to the cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge shows 1
