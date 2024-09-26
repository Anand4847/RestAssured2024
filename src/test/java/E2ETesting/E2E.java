package E2ETesting;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import E2EPojo.CreateOrderReq;
import E2EPojo.LoginReq;
import E2EPojo.LoginRes;
import E2EPojo.OrderDetails;
import E2EPojo.ViewOrderDetail;
import E2EPojo.Dataextract;
import E2EPojo.DelResponse;
import E2EPojo.DeleteOrderResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class E2E {

	public static void main(String[] args) {

		// Login into the application and fetching the userid and token from the
		// response

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginReq loginrequest = new LoginReq();
		loginrequest.setUserEmail("jiman3600@gmail.com");
		loginrequest.setUserPassword("Jiman@123");

		RequestSpecification reqLogin = given().log().all().spec(req).body(loginrequest);

		LoginRes LoginRes = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginRes.class);

		String token = LoginRes.getToken();
		String userid = LoginRes.getUserId();

		System.out.println(LoginRes.getToken());
		System.out.println(LoginRes.getMessage());
		System.out.println(LoginRes.getUserId());

		// ************************************************************************

		// Creating a new product

		RequestSpecification addproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqaddproduct = given().log().all().spec(addproductbasereq).param("productName", "Laptop")
				.param("productAddedBy", userid).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Lenovo").param("productFor", "men")
				.multiPart("productImage", new File("C:\\Users\\admin\\Desktop\\issue.png"));

		String response = reqaddproduct.when().post("/api/ecom/product/add-product").then().log().all().statusCode(201)
				.extract().response().asString();

		JsonPath jp = new JsonPath(response);
		String productid = jp.getString("productId");

		System.out.println(productid);

		// ************************************************************************

		// Placing a new order

		RequestSpecification createorderBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productid);

		// As it is expecting the list so we create list

		List<OrderDetails> orderdetailist = new ArrayList<OrderDetails>();
		orderdetailist.add(orderdetails);

		// Adding the list to the main set order

		CreateOrderReq order = new CreateOrderReq();
		order.setOrders(orderdetailist);

		RequestSpecification createOrdereq = given().log().all().spec(createorderBasereq).body(order);

		String responseaddorder = createOrdereq.when().post("/api/ecom/order/create-order").then().log().all().extract()
				.response().asString();

		JsonPath js1 = new JsonPath(responseaddorder);
		List<String> orderid = js1.getList("orders");

		// Viewing the order details after placing an order

		RequestSpecification viewordereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).addQueryParam("id", orderid).build();

		ViewOrderDetail orderespdetails = given().log().all().spec(viewordereq).when()
				.get("/api/ecom/order/get-orders-details").then().log().all().statusCode(200).extract().response()
				.as(ViewOrderDetail.class);

		System.out.println(orderespdetails.getData().getProductDescription());

		// Delete Request of Product

		RequestSpecification Deletereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification delreq = given().log().all().spec(Deletereq).pathParam("productId", productid);

		DelResponse deleterequest = delreq.when().delete("/api/ecom/product/delete-product/{productId}").then().log()
				.all().assertThat().statusCode(200).extract().response().as(DelResponse.class);

		System.out.println(deleterequest.getMessage());

	}

}
