package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_06_Data_Provider {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//input[@id='email']");
	By passwordTextbox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="user_pass")
	public void TC_01_LoginToSystem(String username, String password) throws InterruptedException{
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		
		// Action...
			
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}
	
	@DataProvider(name="user_pass")
	public Object[][] UserAndPasswordData(){
		return new Object[][] {
			{"selenium_11_01@gmail.com","111111"},
			{"selenium_11_02@gmail.com","111111"},
			{"selenium_11_03@gmail.com","111111"}};
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
