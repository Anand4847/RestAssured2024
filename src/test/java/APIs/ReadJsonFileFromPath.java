package APIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReadJsonFileFromPath {

	
	
	public static void main(String[] args) throws Exception {

		//1. Content of the file to String -> Content of the file can convert into Byte -> Byte data to String
		//2. So after that we need to provide new String to convert from byte to string

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\Addplace.json")))).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();

		
		JsonPath js  = new JsonPath(response);// Jsonpath is used to parse json, now the js has knowledge of response var
        String placeid = js.getString("place_id"); 
		
        
        System.out.println(placeid);
        
	}
}
