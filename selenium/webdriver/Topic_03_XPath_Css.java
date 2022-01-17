package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		driver.get("http://live.techpanda.org/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		
	}

	@Test
	public void TC_02() {
		System.out.println("1 - Sendkey to Email textbox by: ID");
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.id("email")).clear();
		
		System.out.println("2 - Sendkey to Email texbox by: Class");
		driver.findElement(By.className("validate-email")).sendKeys("class@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.className("validate-email")).clear();
		
		System.out.println("3 - Sendkey to Email texbox by: Name");
		driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.name("login[username]")).clear();
		
		System.out.println("4 - Sendkey to Email texbox by: Css");
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("css@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[id='email']")).clear();
		
		System.out.println("5 - Link Text => work vs link");
		driver.findElement(By.linkText("SITE MAP")).click();
		sleepInSecond(1);
		
		System.out.println("6 - Partial Link Text => work vs link");
		driver.findElement(By.partialLinkText("CONTACT")).click();
		sleepInSecond(1);
		
		System.out.println("7 - Tagname");
		sleepInSecond(1);
		System.out.println("All links on Contact page = " + driver.findElement(By.tagName("a")).getSize());
		
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		
		
		System.out.println("8 - Xpath cover ID");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpathcoverid@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		
		System.out.println("8 - Xpath cover Class");
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("xpathcoverclass@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).clear();
		
		System.out.println("8 - Xpath cover Name");
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("xpathcovername@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
		
		
		
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}