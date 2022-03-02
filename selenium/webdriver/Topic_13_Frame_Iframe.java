package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		
		// Switch vào iframe Facebook
		//driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		String kynaFacebookLikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(kynaFacebookLikes, "167K likes");
		
		// Switch về trang parent
		driver.switchTo().defaultContent();
		
		// Switch vào frame Chat
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jonh Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Register new Course");
		sleepInSecond(2);
		
		// Switch về trang parent
		driver.switchTo().defaultContent();
		
		String keyword = "Excel";
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button>img")).click();
		sleepInSecond(3);
		
		List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
		
		for (WebElement course : courseNames) {
			System.out.println(course.getText().toLowerCase());
			Assert.assertTrue(course.getText().toLowerCase().contains(keyword.toLowerCase()));
		}
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// Switch vào frame
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		// Verify password textbox is display
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		
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