package Sel.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Sel.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests2 {

	public WebDriver alone;
	public LoginPage lp;
	public Properties p;
	
	
	public Properties dynamicValues() throws IOException
	{
		p = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Sel//Resources//Global.properties");
		p.load(file);
		return p;
		
	}
	public WebDriver initializeBrowser() throws IOException
	{
		dynamicValues();
		if(p.getProperty("browser").contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			alone = new ChromeDriver();
		}
		alone.manage().window().maximize();
		alone.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return alone;
	}
	
	public String getScreenshot(String testCastName,WebDriver alone) throws IOException

	{
		File src = ((TakesScreenshot)alone).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//"+ testCastName + ".png"));
		return System.getProperty("user.dir")+"//reports//"+ testCastName + ".png";
	}
	@BeforeMethod(alwaysRun=true)
	public LoginPage lauchBrowser() throws IOException
	{
		alone =initializeBrowser();
		lp = new LoginPage(alone);
		lp.goURl();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closingBrowser()
	{
		alone.close();
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper obj = new ObjectMapper();
		List<HashMap<String,String>> dataReader = obj.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return dataReader;
	}
	
}
