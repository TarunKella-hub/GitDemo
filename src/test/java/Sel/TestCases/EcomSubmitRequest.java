package Sel.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

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
import Sel.pageobjects.CartAndCheckout;
import Sel.pageobjects.LoginPage;
import Sel.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EcomSubmitRequest extends BaseTests2{

	
	
	
	@Test(dataProvider = "getData", groups="Purchase")
	
	public void sumbitReuest(HashMap<String,String> input) throws IOException, InterruptedException
	{
		//String userName = p.getProperty("userName");
		//String passWord = p.getProperty("passWord");
		String prNeed = p.getProperty("prNeed");
		String country = p.getProperty("country");
		
		ProductCatalog pc = lp.LoginDetails(input.get("email"), input.get("password"));
		pc.addToCart(prNeed);
		CartAndCheckout cac= pc.goToCart();
		boolean match = cac.verifySelectedProducts(prNeed);
		Assert.assertTrue(match);
		cac.goToCheckout();
		cac.selectCountry(country);
		cac.selectPlaceholder();
		String confirmMessage = cac.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"sumbitReuest"})
	public void orderPageRequest()
	{
		String userName = p.getProperty("userName");
		String passWord = p.getProperty("passWord");
		String prNeed = p.getProperty("prNeed");
		//String country = p.getProperty("country");
		
		ProductCatalog pc = lp.LoginDetails(userName, passWord);
		CartAndCheckout cac= pc.orderPage();
		Assert.assertTrue(cac.verifyOrderPage(prNeed));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> dataJson = getJsonData(System.getProperty("user.dir")+"//src//test//java//Sel//DataSource//Datasource.json");
		//Object[][] data = new Object[][] {{"tarun17@gmail.com","@Tarun17","IPHONE 13 PRO"}};
		return new Object[][]  {{dataJson.get(0)}, {dataJson.get(1) } };
	}

}
