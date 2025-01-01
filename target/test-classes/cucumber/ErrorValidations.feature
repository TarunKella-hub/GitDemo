
@tag
Feature: Add Product to the cart
  I want to use this template for my feature file

	Background:
	Given URL is hitted.
	
	
  @ErrorValidations
  Scenario Outline: Adding the Product into the cart
    Given Login into the site with username <name> and password <password>
    Then I verify the message "Incorrect email  password." in loginPage.

    Examples: 
      | name  						| 		password 		| 			
      | tarun17@gmail.com |     @Tar17 			| 
      
