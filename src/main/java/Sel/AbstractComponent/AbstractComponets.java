package Sel.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Sel.pageobjects.CartAndCheckout;

public class AbstractComponets {

	WebDriver alone;

	public AbstractComponets(WebDriver alone) 
	{
		this.alone=alone;
	}

		@FindBy(css="[routerlink*='cart']") WebElement cartClick;
		@FindBy(css="[routerlink*='myorders']") WebElement order;
		
	public void waitForAppear(By app)
	{
		WebDriverWait wait = new WebDriverWait(alone,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(app));
	}
	
	public void waitForDisappear(WebElement animate) throws InterruptedException
	{
		Thread.sleep(1000);
		/*WebDriverWait wait = new WebDriverWait(alone,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(animate));*/
	}
	
	public void waitForErrorMessage(WebElement errorMess)
	{
		WebDriverWait wait = new WebDriverWait(alone,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errorMess));
	}
	
	public CartAndCheckout goToCart()
	{
		cartClick.click();
		return new CartAndCheckout(alone);
	}
	
	public CartAndCheckout orderPage()
	{
		order.click();
		return new CartAndCheckout(alone);
	}
		
}
