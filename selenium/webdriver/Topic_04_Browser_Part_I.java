package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_I {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Browser() {
		//các hàm/method để thao tác với browser là thông qua biến driver
		
		//hàm mở ra 1 URL
		driver.get("https://tiki.vn/"); //**
		driver.get("https://www.facebook.com/");
		
		// Đóng browser nếu chỉ có 1 tab
		// Đóng tab hiện tại nếu có nhiều tab
		// Bài học WebDriver API - Windows/ Tabs
		driver.close(); //*
		
		// Đóng browser ko quan tâm bao nhiêu tab
		driver.quit(); //**
		
		// Tìm 1 element trên page
		// Trả về data type là WebElement
		WebElement emailTextbox = driver.findElement(By.id("email")); //**
		
		// Tìm nhiều hơn 1 element trên page
		// Trả về data type là List<WebElement>
		List<WebElement> links = driver.findElements(By.xpath("//a")); //**
		
		// Trả về URL của page hiện tại
		String homePageUrl = driver.getCurrentUrl(); //*
		// Verify tuyệt đối
		Assert.assertEquals(homePageUrl, "https://demo.nopcommerce.com/");
		
		// Lấy ra source code của trang hiện tại (HTML/ CSS/ JS/ JQuery/ ..)
		// Verify tương đối 1 giá trị nào đó có trong trang
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Welcome to our store"));
		Assert.assertTrue(homePageSource.contains("Online shopping is the process consumers go through to purchase products or services over the Internet. You can edit this in the admin site."));
		
		// Lấy ra/ trả về title của page hiện tại
		String homePageTitle = driver.getTitle(); //*
		Assert.assertTrue(homePageTitle.contains("nopCommerce demo store"));
		
		// WebDriver API - Windows/ Tabs
		// Trả về ID của tab hiện tại (1)
		String signUpTabID = driver.getWindowHandle(); //*
		
		// Trả về ID của tất cả các tab đang có (n)
		Set<String> allTabID = driver.getWindowHandles(); //*
		
		// Xử lí cookie (Framework)
		driver.manage().getCookies(); //*
		
		// Lấy log của browser ra (Framwork)
		//driver.manage().logs();		
		
		// Time của việc findElement/ findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //**
		
		// Time page được load xong
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		// Time để 1 đoạn async script được thực thi thành công ( JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		// Fullsreen browser
		driver.manage().window().fullscreen();
		
		// End User hay dùng maximize
		driver.manage().window().maximize(); //*
		
		
		//Test GUI
		// Lấy ra vị trí của browser so với độ phân giải màn hình
		Point browserPosition = driver.manage().window().getPosition();
		
		// Set vị trí của browser tai điểm 0 x 250
		driver.manage().window().setPosition(new Point(0, 250));
		
		// Lấy ra chiều rộng/ chiều cao của browser
		Dimension browserSize = driver.manage().window().getSize();
		
		// Set browser mở với kích thước nào
		// Test Responsive
		driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		// Quay lại trang trước đó
		driver.navigate().back();
		
		// chuyển tiếp tới trang trước đó
		driver.navigate().forward();
		
		// Tải lại trang
		driver.navigate().refresh();
		
		driver.navigate().to("https://www.facebook.com/");
		
		// WebDriver API - Alert/ Authentication Alert
		driver.switchTo().alert(); //**
		
		// WebDriver API - Frame/ Iframe
		driver.switchTo().frame(1); //**
		
		// WebDriver API - Windows/ Tabs
		driver.switchTo().window(""); //**
	
		
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