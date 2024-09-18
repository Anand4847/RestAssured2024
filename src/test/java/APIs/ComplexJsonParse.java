package APIs;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.CoursePrice());
		// Print the total courses count

		int count = js.getInt("courses.size()");
		System.out.println("Course Count is : " + count);
		
		// Print Purchase Amount
		
		int purchaseamount = js.getInt("dashboard.purchaseAmount");

		System.out.println("Purchase Amount is  : "+purchaseamount);
		
		// Print the title of the first course
		
		String firstcoursetitle = js.getString("courses[0].title");
		System.out.println(firstcoursetitle);
		
		// Print all courses titles and their respective prices
		
		for (int i=0 ; i<count ; i++)
			
		{
			
			int price = js.getInt("courses["+i+"].price");
			String coursetitles =js.getString("courses["+i+"].title");
			
			System.out.println("***********************");
			System.out.println(coursetitles);
			System.out.println(price);
		}
		
		// Print number of courses sold by RPA
		
            
		
		  for (int i= 0 ; i<count ; i++)
			  
		  {
			  
			  String courses = js.getString("courses["+i+"].title");
			  
			  if (courses.equalsIgnoreCase("RPA"))
				  
			  {
				  
				  int copy =js.get("courses["+i+"].copies");
				  System.out.println(copy);
				  break;
			  }
		  }
		  
		  
		
		  
		  
	}
}
