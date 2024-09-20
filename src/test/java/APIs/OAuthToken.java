package APIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;

import org.testng.Assert;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class OAuthToken {
	public static void main(String[] args) {
		
		
		// Post Request for Generating a token
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given()
		.log()
		.all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when()
		.post("/oauthapi/oauth2/resourceOwner/token")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response()
		.asString();
		
		JsonPath js = new JsonPath(response);
		String token = js.getString("access_token");
	
		
		// Get Request for authorization of Course details with token value
		
		
		
		given()
		.log()
		.all()
		.queryParam("access_token", token)
		.when()
		.get()
		.then()
		.assertThat()
		.statusCode(200);

	}

}
