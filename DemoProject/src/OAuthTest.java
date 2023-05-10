import static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import pojo.Api;
import pojo.GetCourse;

public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Get auth_code
		//Cannot use below, as the URL along with parameters should be entered in the browser.
//		String responseAuthCode = given().log().all()
//				.queryParams("scope", "https://www.googleapis.com/auth/userinfo.email")
//				.queryParams("auth_url", "https://accounts.google.com/o/oauth2/v2/auth")
//				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//				.queryParams("response_type","code")
//				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//			.when()
//				.post("https://accounts.google.com/o/oauth2/v2/auth").asString();
		
		//For Selenium: tag_name[attribute='value']
		System.setProperty("web.chrome.drive", "path_to_chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("google_uri_for_responseAuthCode");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("xx@xx.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER); 
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		
		//
		String url = driver.getCurrentUrl(); //Do it manually if above automation is blocked by google
		//ask deve to increase the scope
		//Parse this URL and extract only "code" param
		String responseAuthCode = url.split("code=")[1] // partial code
		.split("&scope")[0].toString();
		// responseAuthCode = files.ReusableMethods.getParamValueFromJson(responseAuthCode, "code");
			
		//Get access_token
		String responseAccessToken= given().log().all()
			.urlEncodingEnabled(false) // to disable the URL encoding that has special characters
			.queryParams("code", responseAuthCode)
			.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type","authorization_code")
		.when()
			.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		responseAccessToken = files.ReusableMethods.getParamValueFromJson(responseAccessToken, "access_token");
		
		//Check query parameters to get response.
		String responseGet = given().queryParam("access_token", responseAccessToken).log().all()
							.when()
								.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(responseGet);
		
		GetCourse getCourseResponse = given()
				.queryParam("access_token", responseAccessToken)
				.expect().defaultParser(Parser.JSON) // default parser of Json
				.when()
					.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class); // Parent Class
		
		//GetCourse obj has all the data which can be get by using getters
		String apiCourses= getCourseResponse.getCourses().getApi().get(1).getCourseTitle();
		 List<Api>apiCourses_list= getCourseResponse.getCourses().getApi();//.get(1).getCourseTitle();
		 for (int i = 0; i < apiCourses_list.size(); i++) {
			if(apiCourses_list.get(i).getCourseTitle().equalsIgnoreCase("course_title")) {
				apiCourses_list.get(i).getPrice();
			}
		}
		
	}

}
