package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//@Listeners(testng.Test_Listener.class)
public class Topic_08_Depend_On_Test {
	
	@Test()
	public void TC_01_Create_User() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "TC_01_Create_User")
	public void TC_02_View_User() {
		
	}
	
	@Test(dependsOnMethods = "TC_02_View_User")
	public void TC_03_Edit_User() {
		
	}
	
	@Test(dependsOnMethods = "TC_03_Edit_User")
	public void TC_04_Delete_User() {
		
	}
	
}
