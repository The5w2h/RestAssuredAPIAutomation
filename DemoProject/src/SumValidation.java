//import org.testng.annotations.Test;
import io.restassured.path.json.*;

import org.testng.annotations.Test;

import files.Payload;

public class SumValidation {
	
	@Test
	public void totalAmountPurchased() 
	{
		Integer total = 0;
		JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
		for (int i = 0; i < jsonPath.getInt("courses.size()"); i++) {
			total =+ (jsonPath.getInt("courses["+i+"].price") * jsonPath.getInt("courses["+i+"].copies"));
		}
		System.out.println("Total Amount Purchased");
		System.out.println("======================");
		System.out.println(total);
		System.out.println("======================");
	}
	

}
