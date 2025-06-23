Feature: Sort products

  Background:
    Given the Swag Labs login page is open
    And the user logs in as "standard_user" with password "secret_sauce"

  @regression
  Scenario: Sort products by price low to high
    When the user sorts products by "price low to high"
    Then the products are listed by price low to high
