package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Driver ID = " + driver.toString());
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Only_Two_Windows_Or_Tab() {
		// Page A: Parent
		driver.get("https://automationfc.github.io/basic-form/");
		
		// Lấy ra ID của 1 tab/ windows mà driver đang đứng (active)
		String parentTabID = driver.getWindowHandle();
		
		// Click vào Google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		// Switch qua Google tab thành công
		switchToTabByID(parentTabID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		String googleTabID = driver.getWindowHandle();
		
		// Switch về Parent tab
		switchToTabByID(googleTabID);
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
	}

	
	public void TC_02_Greater_Than_One_Windows_Or_Tab() {
		// Page A: Parent
		driver.get("https://automationfc.github.io/basic-form/");
				
		// Click vào Google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		// Switch vào Google tab
		switchToTabByTitle("Google");
				
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
				
		// Switch về Parent tab
		switchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
				
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/");
				
		// Click vào Facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		// Switch qua tab Facebook
		switchToTabByTitle("Facebook – log in or sign up");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		
		// Switch về Parent tab
		switchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		// Click vào Lazada Link
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		
		// Switch qua tab Lazada
		switchToTabByTitle("Shopping online - Buy online on Lazada.vn");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.lazada.vn/");
		
	}
	
	
	public void TC_03_Greater_Than_One_Windows_Or_Tab() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Click vào button Add to Compare của sản phẩm (Samsung)
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		// Click vào button Add to Compare của sản phẩm (Sony)
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		// Click vào compare
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		// Switch về trang compare
		switchToTabByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");
		
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInSecond(2);
		
		// Switch về trang ban đầu
		switchToTabByTitle("Mobile");
		sleepInSecond(2);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
	}
	
	@Test
	public void TC_04_Exercise_Windows_Tab() {
		driver.get("https://dictionary.cambridge.org/vi/");
		
		// Lấy ra ID của 1 tab/ windows mà driver đang đứng (active)
		String parentTabID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		
		// Switch qua tab thành công
		switchToTabByID(parentTabID);
		
		// Click login button
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("tunguyen96spkt@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("automation@123");
		
		// Click login button
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		sleepInSecond(2);
		
		String dictionaryTabID = driver.getWindowHandle();
		
		// Switch về Parent tab
		switchToTabByID(dictionaryTabID);
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-label='Xem các lựa chọn của người dùng']//span")).getText(), "lex turn");
		
	}
	
	// Chỉ đúng cho duy nhất 2 tab/ windows
	public void switchToTabByID(String expectedID) {
		// Lấy hết tất cả ID của tab/ window đang có
		Set<String> allTabIDs = driver.getWindowHandles();
		
		// Dùng vòng lặp để duyệt qua từng ID một
		for (String id : allTabIDs) {// id: 1 biến tạm để duyệt (so sánh)
			// ID nào mà khác với expectedID truyền vào thì switch qua
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
		
	}
	
	// Đúng cho tất cả trường hợp: 2 hoặc nhiều hơn 2 đều đúng
	public void switchToTabByTitle(String expectedTitle) {
		// Lấy hết tất cả ID của tab/ window đang có
		Set<String> allTabIDs = driver.getWindowHandles();
				
		// Dùng vòng lặp để duyệt qua từng ID một
		for (String id : allTabIDs) {// id: 1 biến tạm để duyệt (so sánh)
			// Switch vào từng tab trước rồi kiểm tra sau
			driver.switchTo().window(id);
			
			// Lấy ra title của tab vừa switch vào
			String actualTitle = driver.getTitle();
			
			// Kiểm tra nếu title bằng với title mong muốn
			if(actualTitle.equals(expectedTitle)) {
				// Thoát khỏi vòng lặp
				break;
			}	
		}
		
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