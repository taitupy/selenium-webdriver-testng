package webdriver;

import java.util.Date;
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

public class Topic_18_Wait_Part_V_Mix_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}


	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		
		By emailIDBy = By.id("email");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit = " + getTimeNow());
		driver.findElement(emailIDBy).isDisplayed();
		System.out.println("End implicit = " + getTimeNow());
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		System.out.println("End explicit = " + getTimeNow());
	}
	
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");
		
		By emailIDBy = By.id("vietnam");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit = " + getTimeNow());
		try {
			driver.findElement(emailIDBy).isDisplayed();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		System.out.println("End implicit = " + getTimeNow());
	}
	

	public void TC_03_Element_Not_Found_Only_Explicit_By() {
		driver.get("https://www.facebook.com/");
		By emailIDBy = By.id("vietnam");
		
		// Implicit = 0
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimeNow());
		try {
			// Nhận tham só By -> ko ảnh hưởng đến implicit
			// Wrapper việc findElement trong chính hàm visibilityOfElementLocated -> 15s của explicit
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		} catch (Exception e) {
		}
		System.out.println("End explicit = " + getTimeNow());
	}

	public void TC_04_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		By emailIDBy = By.id("vietnam");
		
		// 1 - Implicit < Explicit
		// 2 - Implicit = Explicit
		// 3 - Implicit > Explicit
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Start implicit = " + getTimeNow());
		try {
			driver.findElement(emailIDBy).isDisplayed();
		} catch (Exception e) {
		}
		System.out.println("End implicit = " + getTimeNow());
		
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
		} catch (Exception e) {
		}
		System.out.println("End explicit = " + getTimeNow());
	}
	
	@Test
	public void TC_05_Element_Not_Found_Only_Explicit_WebElement() {
		driver.get("https://www.facebook.com/");
		
		// Implicit = 0
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println("Start explicit = " + getTimeNow());
		try {
			// findElement là 1 tham số riêng : nó sẽ chạy độc lập trước
			// Pass thì mới vào hàm: visibilityOf để chạy tiếp
			// Fail thì ko có vào
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("vietnam"))));
		} catch (Exception e) {
		}
		System.out.println("End explicit = " + getTimeNow());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}