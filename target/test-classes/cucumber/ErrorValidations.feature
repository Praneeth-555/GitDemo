
@tag
Feature: Error Validation 
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on E-Commerce page
    When Logged in with the username <name> and password <password>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | name  									| password								 |
      | praneeth@gmail.com 			|#praneeth*K12					   | 
      
