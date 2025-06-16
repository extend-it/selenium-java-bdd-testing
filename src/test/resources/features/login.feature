Feature: Login

  @smoke
  Scenario: Log in with valid credentials
    Given the Swag Labs login page is open
    When the user logs in as "standard_user" with password "secret_sauce"
    Then the user lands on the inventory page
