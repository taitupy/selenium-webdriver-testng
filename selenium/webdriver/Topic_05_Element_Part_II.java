package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		
		String searchPlaceholder = driver.findElement(By.id("search")).getAttribute("placeholder");
		System.out.println(searchPlaceholder);
		
		String instructionText = driver.findElement(By.cssSelector("p.form-instructions")).getText();
		System.out.println(instructionText);
	}

	public void TC_02() {
		driver.get("https://www.facebook.com/");
		
		WebElement loginButton = driver.findElement(By.name("login"));
		System.out.println(loginButton.getCssValue("background-color"));
		System.out.println(loginButton.getCssValue("font-size"));
		System.out.println(loginButton.getCssValue("width"));
		System.out.println(loginButton.getCssValue("font-family"));
	}

	public void TC_03() {
		driver.get("https://www.facebook.com/");
		
		WebElement loginButton = driver.findElement(By.name("login"));
		String loginButtonTagname = loginButton.getTagName();
		
		// Other step...
		
		// Đầu ra của step này sẽ là đầu vào của step kia
		loginButton = driver.findElement(By.xpath("//" + loginButtonTagname + "[@id='loginButton']"));
		// Nối chuỗi
		//button[@id='loginButton']
	}
	
	@Test
	public void TC_04() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("nguyentaitu@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("email")).submit();
		
	}
	
	@Test
	public void TC_05() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.id("name")).sendKeys("Automation FC");
		driver.findElement(By.id("address")).sendKeys("Phu yen");
		driver.findElement(By.id("email")).sendKeys("nguyentaitu@gmail.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		sleepInSecond(3);
		
		driver.findElement(By.id("address")).submit();
		
	}
	
	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}