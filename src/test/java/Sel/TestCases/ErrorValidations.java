package Sel.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Sel.TestComponents.BaseTests;
import Sel.TestComponents.BaseTests2;
import Sel.TestComponents.retry;
import Sel.pageobjects.CartAndCheckout;
import Sel.pageobjects.LoginPage;
import Sel.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTests2{

	

	@Test(groups= {"ErorrHandling"}, retryAnalyzer=retry.class)
	
	public void sumbitReuest() throws IOException, InterruptedException
	{
		String userName = "tarun7@gmail.com";
		String passWord = "@Tarun1";
		String prNeed = "ADIDAS ORIGINAL";
		String country = "india";
		ProductCatalog pc = lp.LoginDetails(userName, passWord);
		String loginErrorMesssage = lp.LoginErrorMessage();
		AssertJUnit.assertEquals(loginErrorMesssage, "Incorrect email  password.");
	}
	
	@Test
	public void verifyselectedProducts() throws InterruptedException
	{
		String userName = "tarun17@gmail.com";
		String passWord = "@Tarun17";
		String prNeed = "ADIDAS ORIGINAL";
		String country = "india";
		ProductCatalog pc = lp.LoginDetails(userName, passWord);
		pc.addToCart(prNeed);
		CartAndCheckout cac= pc.goToCart();
		boolean match = cac.verifySelectedProducts(prNeed);
		AssertJUnit.assertTrue(match);
	}
		/*cac.goToCheckout();
		cac.selectCountry(country);
		cac.selectPlaceholder();
		String confirmMessage = cac.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));*/

}
