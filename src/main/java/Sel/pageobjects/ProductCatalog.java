package Sel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sel.AbstractComponent.AbstractComponets;

public class ProductCatalog extends AbstractComponets{

	WebDriver alone;
	public ProductCatalog(WebDriver alone)
	{
		super(alone);
		this.alone = alone;
		PageFactory.initElements(alone, this);
		
	}
		@FindBy(css=".mb-3") List<WebElement> prodList;
		@FindBy(css=".ng-animating") WebElement animate;
		By app = By.cssSelector(".mb-3");
		By cart = By.cssSelector(".card-body button:last-of-type");
		By ani =By.cssSelector("#toast-container");
	
	public List<WebElement> getProdList()
	{
		waitForAppear(app);
		return prodList;

	}
	
	public WebElement selectProduct(String item)
	{
		WebElement proText = getProdList().stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
		return proText;

	}
	
	public void addToCart(String item) throws InterruptedException
	{
		selectProduct(item).findElement(cart).click();
		waitForAppear(ani);
		waitForDisappear(animate);

	}
}
