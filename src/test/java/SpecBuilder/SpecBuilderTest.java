package SpecBuilder;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import PojoSerielize.AddPlace;
import PojoSerielize.locationdetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

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

		RequestSpecification Req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification Res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		RequestSpecification res = given().log().all().spec(Req).body(ap);

		Response response = res.when().post("/maps/api/place/add/json").then().log().all().spec(Res).extract()
				.response();

		String responseString = response.asString();
		System.out.println(responseString);

	}
}
