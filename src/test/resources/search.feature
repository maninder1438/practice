
@search
Feature: Search
  As an end user
  I want to search for product
  So that i can view product i wish

  @smoke
  Scenario: Search for single product
    Given I am on homepage
    When I search for product "nike"
    Then I should be able to see "nike" product

  @regression
  Scenario Outline: Search multi product
    Given I am on homepage
    When I search for product "<searchItem>"
    Then I should be able to see "<searchItem>" product

    Examples:
      | searchItem |
      | puma       |
      | adidas     |

  @smoke
  Scenario: Verify product rating
    Given I am on homepage
    When I search for product "Apple MacBook Air 2019"
    And Select the product rating "5" from the rating filter
    Then I should be able to see the products of selected rating only
