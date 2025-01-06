
@tag
Feature: 	Purchase the order from E-Commerce Website
  I want to use this template for my feature file

Background:
Given I landed on E-Commerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with the username <name> and password <password>
    When Add product <productName> to cart
    And Checkout <productName>  and submit the order
    Then "Thankyou for the order." message is displayed on the confirmation page

    Examples: 
      | name  									| password								 | productName |
      | praneeth@gmail.com 			|#praneeth*K123						 | QWERTY      |
      
      