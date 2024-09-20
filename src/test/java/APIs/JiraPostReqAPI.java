package APIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraPostReqAPI {

	public static void main(String[] args) {
		
		
		
		RestAssured.baseURI="https://anandsoni-2611.atlassian.net/";
		String response = given()
		.log()
		.all()
		.header("Content-Type","application/json")
		.header("Authorization", "Basic amltYW4zNjAwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3a1U2RW5GRnp0SWpXOEo3VHdzdWZFbzBKXzlvdjEzcE1zdkpBWHdfVWJMak9yOGFBX1Jsdm1wazdrT1hDa2ZYaFdna0hlMy1NVHdNWi0yVWVPVlBrVDQ1LURRWTZLWkRDQ25BYmNNOGhnb1JwbnhFa0VXYlgyck1STW91bmMyR1ZINl9Xd2N6eHJYdGlCQXd0MTBjOFEwZXNteGxUZEdfeW9TYUt4Vy1OWjg9QzlGOTA0MTc=")
		.body(payload.JiraPostReq())
		.when()
		.post("rest/api/2/issue")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(201)
		.extract()
		.response()
		.asString();
		
		JsonPath js = new JsonPath(response);
		String issueid  = js.getString("id");
		
		System.out.println(issueid);
		
		
		// Adding an attachment
		
		given()
		.log()
		.all()
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic amltYW4zNjAwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3a1U2RW5GRnp0SWpXOEo3VHdzdWZFbzBKXzlvdjEzcE1zdkpBWHdfVWJMak9yOGFBX1Jsdm1wazdrT1hDa2ZYaFdna0hlMy1NVHdNWi0yVWVPVlBrVDQ1LURRWTZLWkRDQ25BYmNNOGhnb1JwbnhFa0VXYlgyck1STW91bmMyR1ZINl9Xd2N6eHJYdGlCQXd0MTBjOFEwZXNteGxUZEdfeW9TYUt4Vy1OWjg9QzlGOTA0MTc=")
        .pathParam("key", issueid)
		.multiPart("file",new File("C:\\Users\\admin\\Desktop\\issue.png"))
		.when()
		.post("rest/api/2/issue/{key}/attachments")		
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(200);
		
		// Fetch the created request
		
		
		 given()
		.log()
		.all()
		.header("Authorization", "Basic amltYW4zNjAwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA3a1U2RW5GRnp0SWpXOEo3VHdzdWZFbzBKXzlvdjEzcE1zdkpBWHdfVWJMak9yOGFBX1Jsdm1wazdrT1hDa2ZYaFdna0hlMy1NVHdNWi0yVWVPVlBrVDQ1LURRWTZLWkRDQ25BYmNNOGhnb1JwbnhFa0VXYlgyck1STW91bmMyR1ZINl9Xd2N6eHJYdGlCQXd0MTBjOFEwZXNteGxUZEdfeW9TYUt4Vy1OWjg9QzlGOTA0MTc=")
        .header("Accept","application/json")		
		.when()
		.get("/rest/api/2/issue/10042")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		
		
	}
}
