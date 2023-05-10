import static io.restassured.RestAssured.*;

public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Get auth_code
		String responseAuthCode = given().log().all()
				.queryParams("scope", "https://www.googleapis.com/auth/userinfo.email")
				.queryParams("auth_url", "hhttps://accounts.google.com/o/oauth2/v2/auth")
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("response_type","code")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.when()
				.post("https://accounts.google.com/o/oauth2/v2/auth").asString();
			
		//Get access_token
		String responseAccessToken= given().log().all()
			.queryParams("code", responseAuthCode)
			.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type","authorization_code")
		.when()
			.post("https://www.googleapis.com/oauth2/v4/token").asString();
		//Check query parameters to get response.
		String responseGet = given().queryParam("access_token", responseAccessToken).log().all()
							.when()
								.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(responseGet);
		
		
		
	}

}
