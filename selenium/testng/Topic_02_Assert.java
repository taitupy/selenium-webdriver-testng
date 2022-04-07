package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	@Test
	public void TC_01() {
		String employeeName = "Tran Van Em";
		Object address = null;
		
		// Dùng để kiểm tra 1 điều kiện mong muốn trả về là đúng (true)
		Assert.assertTrue(employeeName.equals("Tran Van Em"));
		//Assert.assertTrue(employeeName.equals("Tran Van Anh"),"Employee is not equal.");
		
		// Dùng để kiểm tra 1 điều kiện mong muốn trả về là sai
		Assert.assertFalse(employeeName.equals("Tran Van Anh"));
		
		// Dùng để kiểm tra 2 điều kiện là bằng nhau
		Assert.assertEquals(employeeName, "Tran Van Em");
		
		Assert.assertNull(address);
		
		address = "Nguyen Van An"; 
		
		Assert.assertNotNull(address);
		
		
		
		
		
	}
}
