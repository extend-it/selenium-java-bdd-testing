Feature: Cart management

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"
    And the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart

  @regression
  Scenario: Remove an item from the cart
    When the user opens the cart
    And the user removes "Sauce Labs Backpack" from the cart
    Then the cart contains "Sauce Labs Bike Light"
    And the cart does not contain "Sauce Labs Backpack"
