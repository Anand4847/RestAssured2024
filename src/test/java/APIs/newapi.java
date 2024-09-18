package APIs;

import static io.restassured.RestAssured.given;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class newapi {

	
	
	public static void main(String[] args) {
		
		
		
		RestAssured.baseURI="https://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();

		String response = given()
		.log()
		.all()
		.header("Content-Type", "application/json")
		.body(payload.librarypostapi())
		.when()
		.post("/Library/Addbook.php")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.response()
		.asString();
		
		
		JsonPath js = new JsonPath(response);
		String idvalue =js.getString("ID");
		
		System.out.println(idvalue);
		
	}
}
