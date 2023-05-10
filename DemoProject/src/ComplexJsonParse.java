import files.Payload;
import io.restassured.path.json.*;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
		System.out.println(jsonPath.getInt("dashboard.purchaseAmount"));
		System.out.println(jsonPath.getInt("courses[0].price") * jsonPath.getInt("courses[0].copies"));
		System.out.println(jsonPath.getInt("courses.size()")); // size() method is used only with Arrays
		
		// Courses Title
		for (int i = 0; i < jsonPath.getInt("courses.size()"); i++) {
			System.out.println(jsonPath.getString("courses["+i+"].title"));
		}
		
		System.out.println("========\nPrint no of copies sold for RPA");
		
		for (int i = 0; i < jsonPath.getInt("courses.size()"); i++) {
			//System.out.println(jsonPath.getString("courses["+i+"].title"));
			if (jsonPath.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				//System.out.println(jsonPath.getInt("courses["+i+"].price"));
				System.out.println(jsonPath.getInt("courses["+i+"].copies"));
				break; // to break out when condition is met.
			}
		}
	}
	
}
