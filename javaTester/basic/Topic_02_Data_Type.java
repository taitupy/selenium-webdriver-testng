package basic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// 2 loại kiểu dữ liệu
		// Kiểu nguyên thủy (Primitive Type)
		//8 loại
		
		//char - kí tự - 2 byte
		char c = 'A';
		System.out.println(c);
		
		//byte - số nguyên - 8 bits
		byte bNumber = 15;
		System.out.println(bNumber);
		
		//short - số nguyên - 16
		short sNumber = -32000;
		System.out.println(sNumber);
		
		//int - số nguyên - 32
		int iNumber = 200000;
		System.out.println(iNumber);
		
		//long - số nguyên - 64
		long lNumber = 9212533;
		System.out.println(lNumber);
		
		//float - số thực - 32 //f phia sau
		float fNumber = 9.5f;
		System.out.println(fNumber);
		
		//double - số thực - 64 //d phia sau
		double dNumber = 9.5d ;
		System.out.println(dNumber);
		
		//boolean - logic - 1 bits (true/ false)
		boolean m = true;
		System.out.println(m);
		  
		// Kiểu tham chiếu( Reference Type)
		// Object
		Object o = new Object();
		 
		// Array
		String[] address = {"Ha Noi", "Sai Gon", "Da Nang"};
		
		Integer[] phone = {00,11,222};
		
		long a[] = {100000L, 300000L, 400000L, 786777L };
		
		// Class
		Topic_02_Data_Type topic = new Topic_02_Data_Type();
		
		// Interface
		WebDriver driver;
		
		// Collection: List/ Set/ Queue
		//List<String> addresses = new ArrayList<String>();
		List<String> addresses = new ArrayList<String>();
		
		
		// String - chuỗi ký tự
		String name = "Automation";
		String cityName = new String("Ho chi minh");
	}

}
