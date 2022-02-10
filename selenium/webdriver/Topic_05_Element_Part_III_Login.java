package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_III_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAdress, password, nonExistedEmailAddress;

	// Global variable
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By loginButtonBy = By.id("send2");

	@BeforeClass // Chạy 1 lần đầu tiên cho tất cả testcase
	public void beforeClass() { // Pre-Condition

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		
		firstName = "Steve";
		lastName = "Job";
		fullName = firstName+ " " + lastName;
		emailAdress = "stevejob" + getRandomNumber() + "@hotmail.net";
		nonExistedEmailAddress = "stevejob" + getRandomNumber() + "@live.com";
		password = "123456789";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeMethod // Lặp lại cho từng testcase
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/index.php/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(loginButtonBy).click();

		// Verify error message display
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");

	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.findElement(emailTextboxBy).sendKeys("546@3453.4543");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();

		// Verify error message display
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void Login_03_Invalid_Password() {
		driver.findElement(emailTextboxBy).sendKeys("automationfc0702@gmail.net");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(loginButtonBy).click();

		// Verify error message display
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void Login_04_Create_New_Account_Success() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Existed Email
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAdress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAdress));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
		
	}

	@Test
	public void Login_05_Incorrect_Email_Or_Password() {
		// Existed Email + incorrect Password -> Unsuccess
		driver.findElement(emailTextboxBy).sendKeys(emailAdress);
		driver.findElement(passwordTextboxBy).sendKeys("123789");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
		// Non existed Email + correct/ valid Password -> Unsuccess
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(nonExistedEmailAddress);
		
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
	}

	@Test
	public void Login_06_Valid_Email_And_Password() {
		driver.findElement(emailTextboxBy).sendKeys(emailAdress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
				
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAdress));
		
	}

	@AfterClass // Post-Condition
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
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	
}