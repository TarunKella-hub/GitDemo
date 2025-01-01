
@tag
Feature: Add Product to the cart
  I want to use this template for my feature file

	Background:
	Given URL is hitted.
	
	
  @Regression
  Scenario Outline: Adding the Product into the cart
    Given Login into the site with username <name> and password <password>
    When I add product <productName> into the cart
    And checkout the page,post placing order
    Then I verify the message "THANKYOU FOR THE ORDER." in displayed.

    Examples: 
      | name  						| 		password 		| productName			|	country	|
      | tarun17@gmail.com |     @Tarun17 		| ADIDAS ORIGINAL	|	India		|
      
