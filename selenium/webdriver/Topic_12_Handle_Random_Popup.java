package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Topic_12_Handle_Random_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		// Trạng thái của element
		explicitWait = new WebDriverWait(driver, 30);
		
		// Để tìm element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		
		// Test thử xem code mình viết nó có coverage được case số 2 ko
		// Cố tình sleep cứng 10s và tự click bằng tay để nó close popup đi
		// Giả lập là app này tại thời điểm ko show popup
		//sleepInSecond(10);
		
		// Hàm findElement chỉ chạy được khi element có trong DOM thôi
		// Dù popup có hiển thị hay ko hiển thị thì element luôn luôn có trong DOM (1 node)
		WebElement supportHomePopup = driver.findElement(By.cssSelector("img#support-home"));
		
		// Nếu như popup hiển thị thì close đi hoặc là xxx lên popup đó
		if (supportHomePopup.isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(2);
		}
		
		// Step tiếp theo dù popup có hiển thị hoặc ko
		// Click vaof PC 64X thì nó bật tiếp lên 1 popup( Layer popup)
		driver.findElement(By.xpath("//div[@id='container']//a[text()='PC 64X']")).click();
		sleepInSecond(2);
		
		// Expected layer popup hiển thị
		WebElement layerPopup = driver.findElement(By.xpath("//img[@class='layer-popup']"));
		Assert.assertTrue(layerPopup.isDisplayed());
		
		// Click để download file cài đặt về
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#down-url")));
		sleepInSecond(2);
	}
	
	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		
		// sleep cứng để close popup đi- giả lập chạy vào else..if
		sleepInSecond(8);
		
		List<WebElement> counpondPopup = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Popup size = " + counpondPopup.size());
		
		// Popup có hiển thị thì xuất hiện element trong DOM( 1 node)
		// Popup bị đóng (ko hiển thị) thì element ko còn tồn tại trong DOM (No matching node)
		
		// Nếu như popup hiển thị thì size của element > 0
		if (counpondPopup.size() > 0) {
			System.out.println("Case 1 - Có hiển thị thì vào close popup đi");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(2);
		}else {
			System.out.println("Case 2 - Ko hiển thị thì qua step tiếp theo");
		}
		
		driver.findElement(By.xpath("//h4[text()='Khóa học thiết kế hệ thống M&E - Tòa nhà']")).click();
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