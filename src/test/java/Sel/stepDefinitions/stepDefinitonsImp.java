package Sel.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import Sel.TestComponents.BaseTests2;
import Sel.pageobjects.CartAndCheckout;
import Sel.pageobjects.LoginPage;
import Sel.pageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitonsImp extends BaseTests2{

	public LoginPage lp;
	public ProductCatalog pc;
	public CartAndCheckout cac;
	
	
	@Given("URL is hitted.")
	public void url_is_hitted() throws IOException
	{
		lp = lauchBrowser();
	}
	
    @Given("^Login into the site with username (.+) and password (.+)$")
    public void login_into_site(String userName,String passWord)
    {
		 pc = lp.LoginDetails(userName,passWord);

    }
    
    @When("^I add product (.+) into the cart$")
    public void i_add_product(String productName) throws InterruptedException
    {
		pc.addToCart(productName);
		cac= pc.goToCart();
		boolean match = cac.verifySelectedProducts(productName);
		Assert.assertTrue(match);
		cac.goToCheckout();
    }
    
    @When("checkout the page,post placing order")
    public void checkout_the_page_post_placing_order() {
    	cac.selectCountry("India");
		cac.selectPlaceholder();
    }
   
    
    @Then("I verify the message {string} in displayed.")
    public void verify(String string)
    {
    	String confirmMessage = cac.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		alone.close();
    }
    
    @Then("I verify the message {string} in loginPage.")
    public void i_verify_the_message(String string)
    {
    	String loginErrorMesssage = lp.LoginErrorMessage();
		AssertJUnit.assertEquals(loginErrorMesssage, "Incorrect email  password.");
    }

    
	
	





}
