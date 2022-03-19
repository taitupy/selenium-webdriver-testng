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
import java.util.Date;

public class Topic_18_Wait_Part_II_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
	}


	public void TC_01_Find_Element() {
		// Có duy nhất 1 element
		// Nếu như element xuất hiện ngay -> Thì trả về element đó ko cần chờ hết timeout
		// Nếu như element chưa xuất hiện -> Thì sau 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		// Trả về duy nhất 1 element
		// System.out.println("Start time = "+ getCurrentTime());
		// driver.findElement(By.xpath("//input[@name='firstname']"));
		// System.out.println("End time = "+ getCurrentTime());
		
		// Không có element nào hết
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		// Sau khi hết timeout thì đánh fail cả testcase này
		// Ko có chạy các steps còn lại
		// Throw / Log ra 1 exception (ngoại lệ): NoSuchElement - ko tìm thấy element
		//System.out.println("Start time = "+ getCurrentTime());
		//driver.findElement(By.xpath("//input[@name='automation']"));
		//System.out.println("End time = "+ getCurrentTime());
		
		// Có nhiều hơn 1 element
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
	}
	
	@Test
	public void TC_02_Find_Elements() {
		int elementNumber = 0;
		// Có duy nhất 1 element
		// Có nhiều hơn 1 element
		// Nếu như element xuất hiện ngay -> Thì trả về element đó ko cần chờ hết timeout
		// Nếu như element chưa xuất hiện -> Thì sau 0.5s sẽ tìm lại cho đến khi hết timeout thì thôi
		elementNumber = driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("1 element = " + elementNumber);
		
		elementNumber = driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("n element = " + elementNumber);
		
		// Không có element nào hết
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout
		// Sau khi hết timeout thì ko đánh fail step này
		// Vẫn chạy tiếp các step tiếp theo
		System.out.println("Start time = "+ getCurrentTime());
		elementNumber = driver.findElements(By.xpath("//input[@name='automation']")).size();
		System.out.println("0 element = " + elementNumber);
		System.out.println("End time = "+ getCurrentTime());
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
	
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
}