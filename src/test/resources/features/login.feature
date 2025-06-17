Feature: Login

  @smoke
  Scenario: Log in with valid credentials
    Given the Swag Labs login page is open
    When the user logs in as "standard_user" with password "secret_sauce"
    Then the user lands on the inventory page

  @negative
  Scenario: Locked out user cannot log in
    Given the Swag Labs login page is open
    When the user logs in as "locked_out_user" with password "secret_sauce"
    Then the login error "Epic sadface: Sorry, this user has been locked out." is shown

  @negative
  Scenario: Wrong password is rejected
    Given the Swag Labs login page is open
    When the user logs in as "standard_user" with password "wrong_password"
    Then the login error "Epic sadface: Username and password do not match any user in this service" is shown

  @negative
  Scenario: Empty credentials are rejected
    Given the Swag Labs login page is open
    When the user logs in as "" with password ""
    Then the login error "Epic sadface: Username is required" is shown
