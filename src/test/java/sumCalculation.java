import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import methods.payload;

public class sumCalculation {
	@Test
	public void purchaseSum() {
		int totalPrice=0;
		JsonPath js=new JsonPath(payload.reponse());
		int count=js.getInt("courses.size()");
		int amount=js.getInt("dashboard.purchaseAmount");
		for(int i=0;i<count;i++) {
			totalPrice=totalPrice+js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
			System.out.print(js.getString("courses["+i+"].title")+" "+js.getInt("courses["+i+"].price"));
			//Print no of copies sold by RPA Course
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(" ");
				System.out.println(js.getString("courses["+i+"].copies"));
			}
		}
		Assert.assertEquals(totalPrice, amount);
		
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		if(totalPrice==amount) {
			System.out.println("Total price matches with purchase amount");
		}
		else {
			System.out.println("Total price does not match with purchase amount");
		}
		
	}

}
