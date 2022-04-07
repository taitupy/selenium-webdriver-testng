package testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Group_User {
	
	@BeforeClass(alwaysRun = true)
	public void initBrowser() {
		System.out.println("Open Browser");
	}
	
	@Test(groups="user")
	public void TC_01_Create_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_02_View_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_03_Edit_User() {
		
	}
	
	@Test(groups= {"user","admin"})
	public void TC_04_Delete_User() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanBrowser() {
		System.out.println("Close Browser");
	}
	
}
