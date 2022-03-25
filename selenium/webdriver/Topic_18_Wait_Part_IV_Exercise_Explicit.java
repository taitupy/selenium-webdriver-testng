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
import java.util.Set;

public class Topic_18_Wait_Part_IV_Exercise_Explicit {
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
	}
	
	@Test
	public void TC_01_Upload_Files() {
		explicitWait = new WebDriverWait(driver, 90);
		
		driver.get("https://gofile.io/uploadFiles");
		
		// Lấy ra ID của 1 tab/ windows mà driver đang đứng (active)
		String parentTabID = driver.getWindowHandle();
		
		//driver.get("https://gofile.io/uploadFiles");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		// Load Files + Uploading
		driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation + "\n" + appiumImageLocation + "\n" + apiImageLocation);
		
		// Wait cho Files được upload thành công trong vòng 90s
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		// Wait cho text được visible
		WebElement uploadedText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		assertTrue(uploadedText.isDisplayed());
		
		// Click download
		driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
		
		// Switch qua Google tab thành công
		switchToTabByID(parentTabID);
		
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[text()='Download']"), 3));
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[text()='Play']"), 3));
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}