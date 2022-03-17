package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_Part_I_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
	}


	public void TC_01_Visible() {
		// Visible: Có trên UI và có trong DOM/ HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		Assert.assertTrue(driver.findElement(By.cssSelector("input#email")).isDisplayed());
		
	}
	

	public void TC_02_Invisible_In_DOM() {
		// Invisible: Ko có trên UI và có trong DOM( Ko bắt buộc)
		// Kết quả như nhau nhưng thời gian chạy của mỗi case khác nhau
		// Chạy mất ~1s
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		// Không hiển thị -> Pass -> ~1s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}
	

	public void TC_02_Invisible_Not_In_DOM() {
		// Invisible: Ko có trên UI và ko có trong DOM( Ko bắt buộc)
		// Kết quả như nhau nhưng thời gian chạy của mỗi case khác nhau
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Chạy mất 15s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		// Không hiển thị -> Failed -> 20s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
	}
	

	public void TC_03_Presence() {
		// Presence: Có trong DOM/ HTML và có trên UI -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// Presence: Có trong DOM/ HTML và ko có trên UI -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_04_Staleness() {
		// Bật Registration form lên
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// Tại thời điểm này Element này đang có trong DOM
		WebElement confirmationEmailAddressTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		// Đóng Registration form lại
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		// Wait cho Confirmation Email Address texbox ko còn trong DOM nữa
		// Vì 1 lý do nào đó mình cần wait cho nó ko còn tồn tại trong DOM nữa
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailAddressTextbox));
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