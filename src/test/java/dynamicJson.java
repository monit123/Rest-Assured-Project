import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import methods.payload;
import methods.reusableMethods;
public class dynamicJson {
	@Test(dataProvider="booksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").log().all().body(payload.addBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		String actualID=reusableMethods.rawToJson(response, "ID");
		
	}
	
	//Delete
	@Test
	public void deleteBook() {
		RestAssured.baseURI="http://216.10.245.166";
		//given().body(actualId)
	}
	
	@DataProvider(name="booksData")
	public Object[][] getData() {
		return new Object[][] {{"7657","gdjtfd"},{"6592","hjwdnj"},{"73053","hedjnsd"}};
	}
}
