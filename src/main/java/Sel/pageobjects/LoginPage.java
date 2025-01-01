package Sel.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sel.AbstractComponent.AbstractComponets;

public class LoginPage extends AbstractComponets{

	
	WebDriver alone;
	
	public LoginPage(WebDriver alone)
	{
		super(alone);
		this.alone=alone;
		PageFactory.initElements(alone, this);
	}
	
	@FindBy(id ="userEmail") WebElement username;
	@FindBy(id ="userPassword") WebElement password;
	@FindBy(id ="login") WebElement submit;
	@FindBy(css="div[aria-label='Incorrect email or password.']") WebElement loginErrorMessage;


	public void goURl()
	{
		alone.get("https://rahulshettyacademy.com/client");
	}
	public ProductCatalog LoginDetails(String userName, String passWord)
	{
	
		username.sendKeys(userName);
		password.sendKeys(passWord);
		submit.click();
		return new ProductCatalog(alone);
	}
	
	public String LoginErrorMessage()
	{
		waitForErrorMessage(loginErrorMessage);
		return loginErrorMessage.getText();
	}
}
