Feature: Inventory

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"

  @smoke
  Scenario: All products are listed
    Then all 6 products are listed
