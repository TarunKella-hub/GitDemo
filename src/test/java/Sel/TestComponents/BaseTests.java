package Sel.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Sel.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {

	public WebDriver alone;
	public LoginPage lp;
	
	public WebDriver initializeBrowser() throws IOException
	{
		Properties p = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Sel//Resources//Global.properties");
		p.load(file);
		p.getProperty("userName");
		if(p.getProperty("browser").contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			alone = new ChromeDriver();
		}
		alone.manage().window().maximize();
		alone.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return alone;
	}
	
	
	@BeforeMethod
	public LoginPage lauchBrowser() throws IOException
	{
		alone =initializeBrowser();
		lp = new LoginPage(alone);
		lp.goURl();
		return lp;
	}
	
	@AfterMethod
	public void closingBrowser()
	{
		alone.close();
	}
	
}
