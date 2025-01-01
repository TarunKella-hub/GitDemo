package Sel.TestCases;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestCase {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver alone = new ChromeDriver();
		String userName = "tarun17@gmail.com";
		String passWord = "@Tarun17";
		String prNeed = "ADIDAS ORIGINAL";
		alone.get("https://rahulshettyacademy.com/client");
		alone.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		alone.findElement(By.id("userEmail")).sendKeys("tarun17@gmail.com");
		alone.findElement(By.id("userPassword")).sendKeys("@Tarun17");
		alone.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(alone,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = alone.findElements(By.cssSelector(".mb-3"));
		WebElement proText = products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(prNeed)).findFirst().orElse(null);
		proText.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(alone.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				//Thread.sleep(1000);
		alone.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = alone.findElements(By.cssSelector(".cartSection h3"));
		boolean match =cartProducts.stream().anyMatch(cp->cp.getText().equalsIgnoreCase(prNeed));
		Assert.assertTrue(match);
		alone.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(alone);
		a.sendKeys(alone.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		alone.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		alone.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = alone.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

		
	}

}
