package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_Part_VI_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}


	public void TC_01() {
		
		// Find Element vs tổng thời gian là 15s
		// Tần số lặp lại để tìm nếu ko thấy là 1s/ 1 lần
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		// Tổng thời gian chờ cho điều kiện
		fluentDriver.withTimeout(Duration.ofSeconds(15))
			// Polling time: lặp lại để tìm điều kiện nếu như chưa thả mãn
			.pollingEvery(Duration.ofSeconds(1))
			// Nếu gặp exception là find ko thấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class)
			
			// Điều kiện
			.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
				}
			});
		
		WebElement loginButton = driver.findElement(By.xpath(""));
		
		fluentElement = new FluentWait<WebElement>(loginButton);
		
		// Setting time
		fluentElement.withTimeout(Duration.ofSeconds(60))
		.pollingEvery(Duration.ofMillis(200))
		.ignoring(ElementNotVisibleException.class);
		
		// Apply điều kiện và trả về String
		String loginButtonText = fluentElement.until(new Function<WebElement, String>() {
			public String apply(WebElement element) {
				return element.getText();
			}
		});
		
		Assert.assertEquals(loginButtonText, "");
		
		// Apply điều kiện và trả về String
		boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				return element.isDisplayed();
			}
		});
		
		// Dữ liệu để dùng cho 1 step khác
		Assert.assertTrue(loginButtonStatus);
	}
	
	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Sau khi bấm Loading icon xuất hiện và mất đi sau 5s
		// Icon loading biến mất = Helloworld text xuất hiện
		
		fluentDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
				System.out.println(text);
				return text;
			}
		});
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