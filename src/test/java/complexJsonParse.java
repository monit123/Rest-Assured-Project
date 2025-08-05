import io.restassured.path.json.JsonPath;
import methods.payload;

public class complexJsonParse {
public static void main(String[] args) {
	//1. Print No of courses returned by API
	JsonPath js=new JsonPath(payload.reponse());
	int count=js.getInt("courses.size()"); 
	System.out.println(count);
	//2.Print Purchase Amount
	int amount=js.getInt("dashboard.purchaseAmount");
	System.out.println(amount);
	//Print Title of the first course
	String firstTitle=js.getString("courses[0].title");
	System.out.println(firstTitle);
	//Print All course titles and their respective Prices
	int totalPrice=0;
	for(int i=0;i<count;i++) {
		totalPrice=totalPrice+js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
		System.out.print(js.getString("courses["+i+"].title")+" "+js.getInt("courses["+i+"].price"));
		//Print no of copies sold by RPA Course
		if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
			System.out.println(" ");
			System.out.println(js.getString("courses["+i+"].copies"));
		}
	}
	//Verify if Sum of all Course prices matches with Purchase Amount
	if(totalPrice==amount) {
		System.out.println("Total price matches with purchase amount");
	}
	else {
		System.out.println("Total price does not match with purchase amount");
	}
	
	
	}	
}
