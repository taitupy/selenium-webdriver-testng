package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Add_Employee() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");

		// Button
		driver.findElement(By.id("btnLogin")).click();
		sleepInSecond(5);

		// At Dashboard page: 'Add Employee' sub-menu is not displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		// Open 'Add Employee' page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// At 'Add Employee' page: 'Add Employee' sub-menu link is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		String firstName = "Luis";
		String lastName = "Suarez";
		String editFirstName = "Steven";
		String editLastName = "Gerrard";
		
		// Enter to FirstName/ LastName
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);

		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");

		// Click Save button
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);
		
		By firstNameTextboxBy = By.id("personal_txtEmpFirstName");
		By lastNameTextboxBy = By.id("personal_txtEmpLastName");
		By employeeIDTextboxBy = By.id("personal_txtEmployeeId");
		

		// Verify 'FirstName/ LastName/ EmployeeID' textbox are disabled (is not enabled)
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());

		// Verify 'FirstName/ LastName/ EmployeeID' value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextboxBy).getAttribute("value"), employeeID);

		// Click Edit button
		driver.findElement(By.cssSelector("input#btnSave")).click();

		// Verify 'FirstName/ LastName/ EmployeeID' textbox are anabled
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextboxBy).isEnabled());

		// Edit 'FirstName/ LastName'
		driver.findElement(firstNameTextboxBy).clear();
		driver.findElement(firstNameTextboxBy).sendKeys(editFirstName);
		
		driver.findElement(lastNameTextboxBy).clear();
		driver.findElement(lastNameTextboxBy).sendKeys(editLastName);

		// Click Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);

		// Verify 'FirstName/ LastName/ EmployeeID' textbox are disabled (is not
		// enabled)
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());

		// Verify 'FirstName/ LastName/ EmployeeID' value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), editLastName);

		// Click to 'Immigration' tab
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		
		// Click to 'Add' button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		// Enter to 'Immigration' number textbox
		driver.findElement(By.id("immigration_number")).sendKeys("31195855");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("Steven's\nPassport\nID");
		sleepInSecond(5);

		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath(" //td[@class='document']/a[text()='Passport']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "31195855");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), "Steven's\nPassport\nID");
		
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