Feature: Checkout

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"
    And the user adds "Sauce Labs Backpack" to the cart
    And the user opens the cart
    And the user proceeds to checkout

  @smoke @regression
  Scenario: Complete a purchase
    When the user fills in checkout information "Jane", "Doe", "12345"
    Then the order total equals the subtotal plus tax
    When the user finishes the order
    Then the order confirmation "Thank you for your order!" is shown
