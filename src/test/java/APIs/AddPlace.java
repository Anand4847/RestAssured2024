package APIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


import org.testng.Assert;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlace {

	// Validate the Add place API working

	// We use given when and then to validate our API's

	// Given -> it will take the input
	// When -> to provide the request details, http method and resource
	// Then -> Validate the response

	public static void main(String[] args) {

		// Creating Post Request

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();

		// Update place -> Update Place with new Address -> Get place to validate new
		// address is present in response

		// Post, Put and Get
		
		JsonPath js  = new JsonPath(response);// Jsonpath is used to parse json, now the js has knowledge of response var
        String placeid = js.getString("place_id"); 
		
        
        System.out.println(placeid);
		
		
		
		// Now updating the place with new address using Put Request
       
        String newAddress = "70 winter walk, USA";
        
      given().log().all()
        .queryParam("key", "qaclick123")
        .header("Content-Type","application/json")
        .body(payload.UpdatePlace(placeid, newAddress))
        .when()
        .put("maps/api/place/update/json")
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200)
        .body("msg", equalTo("Address successfully updated"));
        
        
      // Now using the Get request to validate the new updated address
      
      
      
      String address = given()
      .log()
      .all()
      .queryParam("key", "qaclick123")
      .queryParam("place_id", placeid)
	  .when()
	  .get("maps/api/place/get/json")
      .then()
      .log()
      .all()
      .assertThat()
      .statusCode(200)
      .extract()
      .response()
      .asString();
      
      JsonPath js1 = new JsonPath(address);
      String actualaddress = js1.getString("address");
      
      System.out.println(actualaddress);
      
      
      
      Assert.assertEquals(actualaddress, newAddress);
		
		
		
		
	}
}
