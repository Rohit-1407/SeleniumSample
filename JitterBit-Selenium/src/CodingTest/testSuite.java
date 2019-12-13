package CodingTest;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class testSuite {
	
	String baseUrl = "http://the-internet.herokuapp.com/";
	public WebDriver driver;
	
	@BeforeTest ()
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Rohit\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@Test
	public void firstTest() 
	{
		
	}
	
    @AfterTest ()
    public void afterTest() 
    {
    	driver.close();
    }

}
