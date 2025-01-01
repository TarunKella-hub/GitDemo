package Sel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sel.AbstractComponent.AbstractComponets;

public class CartAndCheckout extends AbstractComponets{

	WebDriver alone;
	public CartAndCheckout(WebDriver alone)
	{
		super(alone);
		this.alone = alone;
		PageFactory.initElements(alone, this);
		
	}
	@FindBy(css=".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css=".totalRow button") WebElement checkout;
	@FindBy(css="[placeholder='Select Country']") WebElement getCountry;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]") WebElement clickCountry;
	@FindBy(css=".action__submit") WebElement placeholder;
	@FindBy(css=".hero-primary") WebElement endMessage;
	@FindBy(xpath="//tr/td[2]") List<WebElement> orderList;
	By sel = By.cssSelector(".ta-results");
	
	public List<WebElement> cartProductList()
	{
		return cartProducts;
	}
	
	public boolean verifySelectedProducts(String prNeed)
	{
		boolean match=cartProductList().stream().anyMatch(cp->cp.getText().equalsIgnoreCase(prNeed));
		return match;

	}
	
	public void goToCheckout()
	{

		checkout.click();
	}
	
	public void selectCountry(String country)
	{
		Actions a = new Actions(alone);
		a.sendKeys(getCountry, country).build().perform();
		waitForAppear(sel);
		clickCountry.click();
	}
	
	public void selectPlaceholder()
	{
		placeholder.click();

	}
	
	public String confirmationMessage()
	{
		return endMessage.getText();
	}
	
	public List<WebElement> orderLists()
	{
		return orderList;
	}
	public boolean verifyOrderPage(String prNeed)
	{
		boolean orderMatch=orderLists().stream().anyMatch(p->p.getText().equalsIgnoreCase(prNeed))	;
		return orderMatch;
	}

}
