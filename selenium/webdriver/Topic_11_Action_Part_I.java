package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_Part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}


	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(By.className("ui-tooltip-content")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
		
	}
	

	public void TC_02_Hover() {
		driver.get("https://www.fahasa.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(5);
		
		//driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']")).click();
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		
	}
	

	public void TC_03_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Total element = " + rectangleNumbers.size());
		
		// release hàm nhả chuột trái
		action.clickAndHold(rectangleNumbers.get(0)).moveToElement(rectangleNumbers.get(7)).release().perform();
		sleepInSecond(5);
		
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Total element selected = " + rectangleNumberSelected.size());
		
		Assert.assertEquals(rectangleNumberSelected.size(), 8);
	}
	
	@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Total element = " + rectangleNumbers.size());
		
		// Đang nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Random ngẫu nhiên
		action.click(rectangleNumbers.get(2))
		.click(rectangleNumbers.get(4))
		.click(rectangleNumbers.get(10))
		.click(rectangleNumbers.get(5))
		.perform();
		
		// Nhả nhấn phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(5);
		
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Total element selected = " + rectangleNumberSelected.size());
		
		Assert.assertEquals(rectangleNumberSelected.size(), 4);
		
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