package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static String getParamValueFromJson(String response, String param)
	{
		JsonPath jsonResponse = new JsonPath(response); // HTTPResponse is the input for JsonPath class
		return jsonResponse.getString(param);
	}
	
}
