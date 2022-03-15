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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Topic_16_Upload_Robot {
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

		// just run for chrome sucessfully
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_One_File_Per_Time() throws IOException, AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Upload 1 file
		// Copy đường dẫn của file vào Clipboard
		StringSelection select = new StringSelection(seleniumImageLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSecond(2);
		loadFileByJavaRobot();
		
		// Upload file thu 2
		// Copy đường dẫn của file vào Clipboard
		select = new StringSelection(appiumImageLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSecond(2);
		loadFileByJavaRobot();
		
		// Upload file thu 3
		// Copy đường dẫn của file vào Clipboard
		select = new StringSelection(apiImageLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
				
		// Click để bật lên Open File Dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		sleepInSecond(2);
		loadFileByJavaRobot();
		
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
	
	public void loadFileByJavaRobot() throws AWTException {
		// Load file
		Robot robot = new Robot();
				
		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
				
		// Nhan xuong Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
				
		// Nha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);
				
		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);		
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