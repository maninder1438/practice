
@login
Feature: Login
  As an end user
  I want to login to my account
  So that i can purchase items

  @smoke
  Scenario: Verify account login page
    Given I am on homepage
    When I click on account button
    Then I should be able to see account login page
    And the message should be visible "Do we know you?"

  @smoke
  Scenario: Verify that the user can login with valid login credentials
    Given I am on account login page
    When  I enter my valid email address "maninder1438@gmail.com"
    And   I enter my valid password "password123456"
    And   Click sign in securely button
    Then  I should be logged in successfully & able to see my first name on top right corner
    And   Sign out link will be visible on top right corner
