package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		// Khởi tạo != null ( Ép kiểu tường minh)
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}
	
	public void TC_01() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[3]//span[text()='Add to Cart']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[3]//span[text()='Add to Cart']");
		sleepInSecond(3);
		
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "jsexecutor" + getRandomNumber() +"@hotmail.com");
		
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		
		String subcribeMessage = getInnerText();
		Assert.assertTrue(subcribeMessage.contains("Thank you for your subscription."));
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
		
	}
	
	public void TC_02() {
		driver.get("https://login.ubuntu.com/");
		sleepInSecond(5);
		
		String emailTextboxXPath = "//input[@id='id_email']";
		WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa-id='login_button']"));
		
		loginButton.click();
		Assert.assertEquals(getElementValidationMessage(emailTextboxXPath), "Please fill out this field.");
		
		driver.findElement(By.xpath(emailTextboxXPath)).sendKeys("123@456@67f$#@");
		loginButton.click();
		
		if (driver.toString().contains("ChromeDriver")) {
			Assert.assertEquals(getElementValidationMessage(emailTextboxXPath), "A part following '@' should not contain the symbol '@'.");
		}else if(driver.toString().contains("FirefoxDriver")) {
			Assert.assertEquals(getElementValidationMessage(emailTextboxXPath), "Please enter an email address.");
		}

	}
	
	@Test
	public void TC_03_Remove_Attribute() {
		String emailAdress = "dam" + getRandomNumber() + "@gmail.com";
		
		driver.get("https://demo.guru99.com/v4/");
		
		driver.findElement(By.cssSelector("input[name='uid']")).sendKeys("mngr391291");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("agudyqy");
		
		driver.findElement(By.cssSelector("input[name='btnLogin']")).click();
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(By.cssSelector("input[name='name']")).sendKeys("John");
		
		driver.findElement(By.xpath("//td[text()='Gender']/parent::tr//td[2]/input[2]")).click();
		
		// Birthday
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(4);
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("1950-01-31");
		sleepInSecond(2);
		//
		
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("1st New York");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("New York");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Michigan");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("112333");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0987654321");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAdress);
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("taitu123");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void openUrl(String url) {
		jsExecutor.executeScript("window.location='" + url + "'");
	}

	public String getPageDomain() {
		return (String) jsExecutor.executeScript("return document.domain");
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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}