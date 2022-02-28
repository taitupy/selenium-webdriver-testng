package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Handle_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Trạng thái của element
		explicitWait = new WebDriverWait(driver, 30);
		
		// Để tìm element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Fix_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		// Ko nên define thành 1 biến WebElement -> có thẻ verfy sai khi tái sử dụng lại biến đó
		// Nên define thành 1 biến By
		By modalLoginPopupBy = By.xpath("//div[@id='modal-login-v1']");
		
		// Kiểm tra ko hiển thị -> Đúng
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
		
		// Click Đăng nhập button
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		
		// Kiểm tra hiển thị -> Đúng
		Assert.assertTrue(driver.findElement(modalLoginPopupBy).isDisplayed());
		
		// Sendkeys
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		// Click button
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		
		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		// Click close button
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		
		// Kiểm tra ko hiển thị -> Đúng
		Assert.assertFalse(driver.findElement(modalLoginPopupBy).isDisplayed());
	}
	

	public void TC_02_Fix_Popup() {
		driver.get("https://bizbooks.vn/");
		
		By loginPopup = By.cssSelector("div#md-signin");
		By registerPopup = By.cssSelector("div#md-signup");
		
		// Cả 2 đều ko hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[@style='display: block;']//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		// Login popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		// Register popup ko hiển thị
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div#md-signin input[name='email']")).sendKeys("automation@hotmail.vn");
		driver.findElement(By.cssSelector("div#md-signin input[name='password']")).sendKeys("automation@hotmail.vn");
		
		driver.findElement(By.cssSelector("button.js-btn-member-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#md-signin span.text-danger")).getText(), "Tài khoản không tồn tại");
		
		// Sendkey 'ESC' vào button Đăng nhập
		driver.findElement(By.cssSelector("button.js-btn-member-login")).sendKeys(Keys.ESCAPE);
		sleepInSecond(2);
		
		// Cả 2 đều ko hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		Assert.assertFalse(driver.findElement(registerPopup).isDisplayed());
	}
	
	@Test
	public void TC_03_Fix_Popup() {
		driver.get("https://jtexpress.vn/");
		
		By homePagePopup = By.cssSelector("div#homepage-popup");
		
		// Chờ cho nó hiển thị sau đó mới verify
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(homePagePopup));
		
		// Verify 
		Assert.assertTrue(driver.findElement(homePagePopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.close")).click();
		
		// Wait cho nó ko hiển thị rồi mới kiểm tra
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(homePagePopup));
		Assert.assertFalse(driver.findElement(homePagePopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys("841000072647");
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(5);
	}


	public void TC_03_Random_Popup_In_DOM() {
		
	}
	
	public void TC_04_Random_Popup_Not_In_DOM() {
		
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