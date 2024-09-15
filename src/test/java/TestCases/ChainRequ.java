package TestCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ChainRequ {

	
	
	public static void main(String[] args) {
		
		// Post request

		{
			
			
		// https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123	
			
		// Given -> all input details
		// When -> Submit the api 
		// Then -> validate the response

		// Query param, header and body will go into the given method
		// Resource name and http method will go into the when 
		// Assertion with status code witll go into then	
			
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")
		            .then().log().all().assertThat().statusCode(200)
		            .body("scope", equalTo("APP"))
		            .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		            
		System.out.println("Response detail is : " +response);
		
		JsonPath  js = new JsonPath(response);
		String placeid = js.get("place_id");
		
		System.out.println("Value of place id is : " +placeid);
		
		
		}
		}
}
