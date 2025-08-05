import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import methods.payload;
import methods.reusableMethods;
public class dynamicJson {
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").log().all().body(payload.addBook("76gf54","776hhgter"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		String actualID=reusableMethods.rawToJson(response, "ID");
		Assert.assertEquals(actualID, "bcd227");
	}
}
