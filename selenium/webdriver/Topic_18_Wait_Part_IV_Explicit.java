package webdriver;

//import java.sql.Date;
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

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Date;

public class Topic_18_Wait_Part_IV_Explicit {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String separatorChar = File.separator;
	String uploadFolderLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	
	// Image name: verify
	String seleniumImage = "1.jpg";
	String appiumImage = "2.jpg";
	String apiImage = "3.jpg";
	
	// Image location: sendkey
	String seleniumImageLocation = uploadFolderLocation + seleniumImage;
	String appiumImageLocation = uploadFolderLocation + appiumImage;
	String apiImageLocation = uploadFolderLocation + apiImage;
	
	// Wait rõ ràng: vs các điều kiện/ status cụ thể
	WebDriverWait explicitWait;
	
	By loadingIcon = By.cssSelector("div#loading");
	By helloworldText = By.cssSelector("div#finish>h4");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//driver.get("https://automationfc.github.io/dynamic-loading/");
	}
	

	public void TC_01_Invisible() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		// Loading icon biến mất sau 5s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	

	public void TC_02_Visible() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		// Loading icon biến mất sau 5s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	

	public void TC_03_Number() throws InterruptedException {
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		// helloworld Text element = 1 ( chờ helloText xuất hiên 1 lần)
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(helloworldText, 1));
		
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	

	public void TC_04_AjaxLoading() {
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Wait cho Date Picker xuất hiện
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		// Element này nó được tìm tại thời điểm mà chưa click ngày 11
		WebElement selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");
		
		// Wait cho ngày 11 có thể click và sau đó click lên nó
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
		
		// Wait loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));
		
		// Sau khi click vào ngày 11 thì element có text nó được cấp nhật lại
		// Nếu như dùng lại element đã được find ở trên rồi getText là sai
		selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		
		// Verify ngày được update
		Assert.assertEquals(selectedDateText.getText(), "Friday, March 11, 2022");
		
		// Wait cho ngày được selected thành công (visible)
		WebElement todaySelected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a")));
		
		// Verify ngày được chọn
		Assert.assertTrue(todaySelected.isDisplayed());
	}
	
	@Test
	public void TC_05_Upload_Files() {
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait = new WebDriverWait(driver, 90);
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		// Load Files + Uploading
		driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation + "\n" + appiumImageLocation + "\n" + apiImageLocation);
		
		// Wait cho Files được upload thành công trong vòng 90s
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		// Wait cho text được visible
		WebElement uploadedText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		assertTrue(uploadedText.isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}