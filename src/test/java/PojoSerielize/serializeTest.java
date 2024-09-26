package PojoSerielize;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class serializeTest {

	public static void main(String[] args) {

		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		locationdetails l = new locationdetails();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		ap.setTypes(myList);
		ap.setWebsite("http://google.com");

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Response response = given().log().all().queryParam("key", "qaclick123").body(ap).when()
				.post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response();

		String responseString = response.asString();
		System.out.println(responseString);

	}
}
