package APIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	// Verify if sum of all course prices matches with Purchase Amount

	@Test
	
	public void validations()
	{
		int sum = 0;


		{

			JsonPath js = new JsonPath(payload.CoursePrice());

			int count = js.getInt("courses.size()");

			for (int i = 0; i < count; i++)

			{

				int price = js.getInt("courses[" + i + "].price");
				int copies = js.getInt("courses[" + i + "].copies");

				int amount = price * copies;

				System.out.println(amount);
				sum = sum + amount;

			}
			System.out.println(sum);
			
			
             int expectedamt = js.getInt("dashboard.purchaseAmount");
			 int actualamt = sum;
			 
			 Assert.assertEquals(sum, expectedamt);
			
		}
	}
}
