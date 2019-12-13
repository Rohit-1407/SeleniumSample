package TestingWebsite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class testSuite {
	
	String baseUrl = "http://the-internet.herokuapp.com/";
	public WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Rohit\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseUrl);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testCasesLogin() {
		
		driver.findElement(By.linkText("Form Authentication")).click();
		
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
		
		WebElement LoginSuccess = driver.findElement((By.id("flash-messages")));
		
		String actualMessage = LoginSuccess.getText();
		String expectedMessage = "You logged into a secure area!";
		
		System.out.println(actualMessage);
		System.out.println(expectedMessage);
		
		Assert.assertEquals(actualMessage, expectedMessage);
		
		driver.findElement((By.linkText("logout"))).click();
		
		driver.findElement(By.id("username")).sendKeys("tomsmith123");
		driver.findElement(By.name("password")).sendKeys("Super");
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
		
		String invalidLogin  = driver.findElement(By.id("flash")).getText();
		
		String expectedError = "Your username is invalid!";
		
		Assert.assertEquals(invalidLogin, expectedError);
	}
	
	@Test
	public void dropDwn() throws InterruptedException {
		
		driver.findElement(By.linkText("Dropdown")).click();
		
		Select drpList = new Select(driver.findElement(By.id("dropdown")));
		
		drpList.selectByIndex(2);
		
		Thread.sleep(5000);
	}
	
	@Test
	public void dynamicTable() {
		
		driver.findElement(By.linkText("Sortable Data Tables")).click();
		
		WebElement table2 = driver.findElement(By.id("table2"));
		
		String expectedEmail = "fbach@yahoo.com";
		
		List<WebElement> rows = table2.findElements(By.cssSelector("tbody > tr"));
		
		for (WebElement singleRow : rows) {
			
			List <WebElement> columns = singleRow.findElements(By.tagName("td"));
			
			String email = columns.get(2).getText();
			
			System.out.println(email);
			if (expectedEmail == email) {
				singleRow.findElement(By.linkText("edit")).click();
			} 	
		}
		
	}

	@AfterTest
	public void afterTest() {
		
		driver.close();
	}

}
