@basket
Feature: Basket
  As an end user
  I want to verify the basket product
  So that i can purchase the right selected product

  Scenario: Verify the product name in the basket
    Given I am on homepage
    When I search for a product of "tripods, monopods and cases"
    And Select the random product from product list
    And add the selected product to basket
    Then I should be able to see the same selected product in the basket

  Scenario: Verify the price updated accurately for a single product but multiple quantity in the basket
    Given I am on homepage
    When I search for a product of "tripods, monopods and cases"
    And Select the random product from product list
    And change the quantity to "3"
    And add the selected product to basket
    Then I should be able to see the correct price in the basket

  Scenario: Verify the price & quantity in the basket when buying multiple products
    Given I am on homepage
    When I search for a product of "ipad pro"
    And Select the random product from product list
    And add the selected product to basket and click continue shopping
    And I search for a product of "hard drive"
    And Select the random product from product list
    And add the selected product to basket and click goto trolley
    Then I should be able to see "2" products in total in the basket
    And The price should be equal to the total of products in the basket