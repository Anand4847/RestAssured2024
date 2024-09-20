package APIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class newapi {

	@Test(dataProvider = "bookdata")

	public void createbook(String aisle, String isbn) {

		// Addbook

		RestAssured.baseURI = "https://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();

		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.librarypostapi(aisle, isbn)).when().post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String idvalue = js.getString("ID");

		System.out.println(idvalue);

	}

	@DataProvider(name = "bookdata")

	public Object[][] getData()

	{

		return new Object[][] { { "ana", "123" }, { "dsoni", "765" }, { "adi", "3f4" } };

	}
}
