import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import methods.reusableMethods;
public class jiraAPI {
	public static void main(String[] args) {
		RestAssured.baseURI="https://mgacademy.atlassian.net/";
		String response=given().header("Content-Type","application/json")
		.header("Authorization","Basic bW9uaXRndXB0YTU4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBsOEtwc3BmUk1xbTJXZDM0MnZrNUVhWnhNbnJpUktwM0tUWmFJUDdaaHV4aTlMVFdQZ2VVMGFjLVFXeXlmYUpvZEQ5SDc3aTYxZGMyaEJWdThTaTAyUWx6TGd6YUVkU25mNjFaUEZpTmxzTWc3REl1cTlxYjFsQnZsTTByYW96amdmZUZDMEZybl9HVFhMYUY4UkZYU1hsMTJZRVBTVTVuOGJQUHVoTGVCZ3M9OEJDNUE3MUU=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"PM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"alert is not displaying\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "")
		.when().log().all().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract().asString();
		String issueId=reusableMethods.rawToJson(response, "id");
		given().pathParam("key", issueId)
		.header("Authorization","Basic bW9uaXRndXB0YTU4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBsOEtwc3BmUk1xbTJXZDM0MnZrNUVhWnhNbnJpUktwM0tUWmFJUDdaaHV4aTlMVFdQZ2VVMGFjLVFXeXlmYUpvZEQ5SDc3aTYxZGMyaEJWdThTaTAyUWx6TGd6YUVkU25mNjFaUEZpTmxzTWc3REl1cTlxYjFsQnZsTTByYW96amdmZUZDMEZybl9HVFhMYUY4UkZYU1hsMTJZRVBTVTVuOGJQUHVoTGVCZ3M9OEJDNUE3MUU=")
		.header("X-Atlassian-Token","no-check").multiPart("file",new File("C:/Users/monit/OneDrive/Pictures/Screenshots/monit.png"))
		.when().post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}
}
