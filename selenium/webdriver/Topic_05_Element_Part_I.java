package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		WebElement element = driver.findElement(By.xpath(""));
		
		// Xóa dữ liệu trong textbox/ textarea/ editable dropdown ( cho phép edit/ nhập liệu)
		element.clear(); //*
		
		// Nhập dữ liệu trong textbox/ textarea/ editable dropdown ( cho phép edit/ nhập liệu)
		element.sendKeys(""); //**
		
		// Click vào button/ radio button/ checkbox/ link/ image/...
		element.click(); //**
		
		// Lấy ra giá trị của Attribute
		element.getAttribute("placeholder"); //*
		
		// Lấy ra text của element
		element.getText(); //**
		
		// Thường dùng để test GUI: font/ color/ size/ location/ position/...
		// Đều lấy ra các thuộc tính của CSS được
		element.getCssValue(""); //*
		
		
		Point elementPoint = element.getLocation();
		Rectangle elementRec = element.getRect();
		Dimension elementSize = element.getSize();
		
		// Chụp hình của element lại lưu bằng format nào đó
		String base64Image = element.getScreenshotAs(OutputType.BASE64); //*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		// Lấy ra tên thẻ (html) của element
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		String elementTagname = element.getTagName();
		
		// Kiểm tra 1 element có hiển thị hay ko - tất cả các loại element
		// Người dùng có thể nhìn thấy được
		
		// Mong đợi nó hiển thị
		Assert.assertTrue(element.isDisplayed()); //**
		
		// Mong đợi nó không hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		// Kiểm tra 1 element có thể thao tác được hay ko
		// Read-only hoặc disable element
		
		// Mong đợi nó thao tác được (enable)
		Assert.assertTrue(element.isEnabled()); //*
		
		// Mong đợi nó bị disable/ read-only/ ko thao tác được
		Assert.assertFalse(element.isEnabled());
		
		// Kiểm tra 1 element đã được chon hay chưa
		// Radio button/ Checkbox/ Dropdown
		element.isSelected();
		
		// Mong đợi nó đã được chọn rồi
		Assert.assertTrue(element.isSelected()); //*
		
		// Mong đợi nó chưa chọn/ bỏ chọn đi
		Assert.assertFalse(element.isSelected());
		
		// Tương ứng vs hành động nhấn phím ENTER trên bàn phím
		// Chỉ dùng vs element là form hoặc nằm trong form
		element.submit();
		
	}

	@Test
	public void TC_02() {
		
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