import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Given: All input details. given().queryParam("key","value").headers("key","value").body("value") i.e., Chain queryParm, headers, body
		// When: Submit the API. when().HTTPVerb("resource") //resource is concatenated with baseURI
		// Then: Validate the response
		// IMP: Use given().log().all().queryParam
		// IMP: To read from file, output should be in String, so first convert to Byte, and convert Byte to String
			// new String(Files.readAllBytes(Paths.get("")))
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// response
		String response = 
				given()//.log().all()
					.queryParam("key", "qaclick123")
					.headers("Content-Type","application/json")
					//.body(Payload.AddPlace()) // Only value is used. Class with static method AddPlace is added in Pay load class
					.body(new String(Files.readAllBytes(Paths.get(""))))
				.when()
					.post("/maps/api/place/add/json")
				.then()//.log().all()
					.assertThat()
						.statusCode(200)
						.body("scope",org.hamcrest.Matchers.equalTo("APP")) // equalTo is used in .body("", equalTo(""))
						.header("server","Apache/2.4.41 (Ubuntu)") // key-value pair
				.extract().response().asString();		
		// Add Place and then Update Place and then GetPlace to assertThat()
		// .extract().response().asString() extracts the entire response
		//System.out.println(response);
		
		
		String placeId = ReusableMethods.getParamValueFromJson(response, "place_id");
		
		System.out.println(placeId);
		
		String newAddress = "70 winter walk updated, USA";
		// PUT
		given().log().all()
			.queryParam("key", "qaclick123")
			//.queryParam("place_id", placeId)
			.header("Content-type","application/json")
			.body("{\n"
					+ "\"place_id\":\""+placeId+"\",\n"
					+ "\"address\":\""+newAddress+"\",\n"
					+ "\"key\":\"qaclick123\"\n"
					+ "}")
		.when()
			.put("maps/api/place/update/json") // resource should be put here.
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.body("msg",org.hamcrest.Matchers.equalTo("Address successfully updated"));
		
		// GET
		
		String getPlaceResponse = given().log().all()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", placeId)
			//.header("Content-type","application/JSON") NOT REQUIRED
		.when()
			.get("maps/api/place/get/json") // resource should be put here.
		.then().log().all()
			.assertThat()
				.statusCode(200)
		.extract().response().asString();
		
		String actualAddress = ReusableMethods.getParamValueFromJson(getPlaceResponse,"address");
		System.out.println(actualAddress);
		
		//CUCUMBER JUNIT and TESTNG
		Assert.assertEquals(actualAddress, newAddress);
		
	}
	
	

}
