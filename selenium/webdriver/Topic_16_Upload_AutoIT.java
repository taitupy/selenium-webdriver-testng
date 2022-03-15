package webdriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_AutoIT {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String separatorChar = File.separator;
	String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	// AutoIt
	String autoITFolderLocation = projectPath + separatorChar + "autoITScripts" + separatorChar;
	
	// Image name: verify
	String seleniumImage = "Selenium.png";
	String appiumImage = "Appium.jpg";
	String apiImage = "API.png";
	
	// Image location: sendkey
	String seleniumImageLocation = uploadFolderLocation + seleniumImage;
	String appiumImageLocation = uploadFolderLocation + appiumImage;
	String apiImageLocation = uploadFolderLocation + apiImage;
	
	// AutoIT script location
	String singleFirefox = autoITFolderLocation + "firefox.exe";
	String singleChromeEdge = autoITFolderLocation + "chromeEdge.exe";
	String multipleFirefox = autoITFolderLocation + "firefoxUploadMultiple.exe";
	String multipleChromeEdge = autoITFolderLocation + "chromeUploadMultiple.exe";
	
	@BeforeClass
	public void beforeClass() {
		// Both Windows + Mac
		if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/geckodriver_mac");
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		}

		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		//driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}


	public void TC_01_One_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		// Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, seleniumImageLocation });
		sleepInSecond(2);
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		// Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, appiumImageLocation });
		sleepInSecond(2);
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		// Load file
		Runtime.getRuntime().exec(new String[] { singleFirefox, apiImageLocation });
		sleepInSecond(2);
		
		// Uploading
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		// Verify uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + seleniumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + appiumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + apiImage + "']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Multiple_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		// Load file
		if(driver.toString().contains("Chrome") || driver.toString().contains("Edge")) {
			Runtime.getRuntime().exec(new String[] { multipleChromeEdge, seleniumImageLocation, apiImageLocation });
		}else {
			Runtime.getRuntime().exec(new String[] { multipleFirefox, seleniumImageLocation, apiImageLocation });
		}
		sleepInSecond(2);
		
		// Uploading
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		
		// Verify uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + seleniumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + apiImage + "']")).isDisplayed());
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