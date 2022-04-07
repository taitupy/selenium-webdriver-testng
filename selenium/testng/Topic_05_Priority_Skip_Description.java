package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority_Skip_Description {
	
	@Test(description = "Create a new User with admin role")
	public void User_01_Create_User() {
		
	}
	
	@Test(description = "JIRA#45928")
	public void User_02_View_User() {
		
	}
	
	@Test()
	public void User_03_Edit_User() {
		
	}
	
	@Test()
	public void User_04_Delete_User() {
		
	}
	
}
