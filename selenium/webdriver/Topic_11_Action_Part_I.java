package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_Part_I {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelperPath = projectPath + "\\dragDropHTML5\\drag_and_drop_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		//Assert.assertTrue(driver.findElement(By.className("ui-tooltip-content")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
		
	}
	

	public void TC_02_Hover() {
		driver.get("https://www.fahasa.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(5);
		
		//driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']")).click();
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		
	}
	
	@Test
	public void TC_02_Hover_And_Iframe() {
		driver.get("https://www.fahasa.com/");
		
		// Switch vào iframe Facebook
		driver.switchTo().frame("preview-notification-frame");
		driver.findElement(By.cssSelector("a#NC_CLOSE_ICON>img")).click();
		sleepInSecond(2);
		
		// Switch về trang parent
		driver.switchTo().defaultContent();
		
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(2);
		
		//driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']")).click();
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage ')]//a[text()='Kỹ Năng Sống']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		
	}
	

	public void TC_03_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Total element = " + rectangleNumbers.size());
		
		// release hàm nhả chuột trái
		action.clickAndHold(rectangleNumbers.get(0)).moveToElement(rectangleNumbers.get(7)).release().perform();
		sleepInSecond(5);
		
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Total element selected = " + rectangleNumberSelected.size());
		
		Assert.assertEquals(rectangleNumberSelected.size(), 8);
	}	
	

	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectangleNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Total element = " + rectangleNumbers.size());
		
		// Đang nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Random ngẫu nhiên
		action.click(rectangleNumbers.get(2))
		.click(rectangleNumbers.get(4))
		.click(rectangleNumbers.get(10))
		.click(rectangleNumbers.get(5))
		.perform();
		
		// Nhả nhấn phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(5);
		
		List<WebElement> rectangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Total element selected = " + rectangleNumberSelected.size());
		
		Assert.assertEquals(rectangleNumberSelected.size(), 4);
		
	}
	

	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(2);
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}
	

	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		// Move mouse to paste mode
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(2);
		
		// Verify paste mode display
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		
		// Click to paste mode
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(2);
		
		// alert display
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: paste");
		driver.switchTo().alert().accept();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
		
	}
	

	public void TC_07_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		// Verify text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		// Verify background color
		String rgbaColor = bigCircle.getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);
		String hexaColor = Color.fromString(rgbaColor).asHex();
		System.out.println("HEXA = " + hexaColor);
		Assert.assertEquals(hexaColor.toLowerCase(), "#03a9f4");
		
	}
	

	public void TC_08_Drag_Drop_HTML5_Css() throws IOException {
		// Chỉ dùng được locator là Css
		// Chỉ dùng với JQuery nên chỉ work với Css
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String collumnACss = "#column-a";
		String collumnBCss = "#column-b";
		
		String dragDropHelperContent = getContentFile(dragDropHelperPath);
		
		dragDropHelperContent = dragDropHelperContent + "$(\"" + collumnACss + "\").simulateDragDrop({ dropTarget: \"" + collumnBCss + "\"});";
		
		// Drag and drop A to B
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
		
		// Drag and drop B to A
		jsExecutor.executeScript(dragDropHelperContent);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "B");
		
	}
	

	public void TC_09_Drag_Drop_HTML5_Xpath() throws AWTException {
		// Dùng Xpath - kéo thả bằng tọa độ
		driver.get("https://automationfc.github.io/drag-drop-html5/");
			
		String collumnAXpath = "//div[@id='column-a']";
		String collumnBXpath = "//div[@id='column-b']";
		
		// Drag/drop from A to B
		dragAndDropHTML5ByXpath(collumnAXpath, collumnBXpath);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "A");
		
		// Drag/drop from B to A
		dragAndDropHTML5ByXpath(collumnAXpath, collumnBXpath);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("#column-b>header")).getText(), "B");
		
		
		
	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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