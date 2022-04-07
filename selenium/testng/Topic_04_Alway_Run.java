package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Alway_Run {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Open Browser");
		
		Assert.assertTrue(false);
	}
	
	@Test(groups="user")
	public void TC_01_Create_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_02_View_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_03_Edit_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_04_Delete_User() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		System.out.println("Close Browser");
		driver.quit();
	}
	
}
