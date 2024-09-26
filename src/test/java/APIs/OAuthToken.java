package APIs;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import PojoDeserialize.Api;
import PojoDeserialize.GetCourses;
import PojoDeserialize.Mobile;
import PojoDeserialize.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuthToken {
	public static void main(String[] args) {

		// Below is the array which we need to convert it to arraylist later on

		String[] courseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		// Post Request for Generating a token

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String token = js.getString("access_token");

		// Get Request for authorization of Course details with token value

		GetCourses gc = given().log().all().queryParam("access_token", token).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);

		System.out.println("*****************************");
		System.out.println("                             ");
		System.out.println(gc.getInstructor());
		System.out.println(gc.getUrl());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getlinkedIn());
		System.out.println(gc.getServices());

		System.out.println("Course title : " + gc.getCourses().getApi().get(1).getCourseTitle());

		List<Api> apicourses = gc.getCourses().getApi();

		for (int i = 0; i < apicourses.size(); i++)

		{

			if (apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))

			{

				String price = apicourses.get(i).getPrice();

				System.out.println(price);
				System.out.println("**************************");
			}
		}

		// Storing the list of course titles into arraylist

		ArrayList<String> actuallist = new ArrayList<String>();

		// 1
		List<WebAutomation> webautcourse = gc.getCourses().getWebAutomation();

		for (int i = 0; i < webautcourse.size(); i++)

		{

			// Storing the list of course titles into arraylist

			actuallist.add(webautcourse.get(i).getCourseTitle());

		}

		List<String> expectedlist = Arrays.asList(courseTitles);

		Assert.assertEquals(actuallist, expectedlist);

		// ********************************************************************

		List<Mobile> mobiledata = gc.getCourses().getMobile();

		for (int i = 0; i < mobiledata.size(); i++) {

			Assert.assertEquals(mobiledata.get(i).getCourseTitle(), "Appium-Mobile Automation using Java");
		}

	}

}
