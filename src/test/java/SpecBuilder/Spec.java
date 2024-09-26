package SpecBuilder;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import PojoSerielize.AddPlace;
import PojoSerielize.locationdetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {

	public static void main(String[] args) {
		
		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		locationdetails ld = new locationdetails();
		ld.setLat(-38.383494);
		ld.setLng(33.427362);
		ap.setLocation(ld);
		ap.setAddress("29, side layout, cohen 09");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> a = new ArrayList<String>();
		a.add("shoe park");
		a.add("shoe park");
		ap.setTypes(a);
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).setBody(ap).build();
		
		
		
		RequestSpecification request = given().spec(req).log().all();
		
		// Seperating the given and when and then merging it with the variable of given
		
		// For Req spec we use set and for Res spec we use expect
		
		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		String response = request
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.assertThat()
		.spec(responseSpec)
		.extract()
		.response()
		.asString();
		
		System.out.println(response);
		
		
	}
}
