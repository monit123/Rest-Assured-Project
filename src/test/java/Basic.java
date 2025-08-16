import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import methods.payload;
import methods.reusableMethods;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
public class Basic {

	public static void main(String[] args) throws IOException {
		//Add place
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("\"C:\\\\Users\\\\monit\\\\addPlace.json\"")))).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").extract().asString();
		System.out.println(response);
		String placeId=reusableMethods.rawToJson(response, "place_id");
		System.out.println(placeId);
		//Update Place
		String address="70 Summer walk, USA";
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json").then().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		//Get Place(Updated data)
		String newAddress=given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();
		Assert.assertEquals(reusableMethods.rawToJson(newAddress, "address"),address);
	}

}
 