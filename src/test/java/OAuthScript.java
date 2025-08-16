import methods.reusableMethods;
import pojo.getCourse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OAuthScript {
	@Test
	public void oAuthData() {
		getCourse m=new getCourse();
		m.setInstructor("monit");
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").body(m).when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all()
				.assertThat().statusCode(200).extract().asString();

		String accessToken = reusableMethods.rawToJson(response, "access_token");
		System.out.println(accessToken);
		
		  getCourse gc=given().queryParam("access_token", accessToken).when()
		 .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log()
		  .all().assertThat().statusCode(401).extract().as( getCourse.class);
		  System.out.println(gc.getLinkedIn());
		  String courseTitle="SoapUI Webservices testing";
		  for(int i=0;i<gc.getCourses().getApi().size();i++) {
			  if(gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase(courseTitle)) {
				  System.out.println(gc.getCourses().getApi().get(i).getPrice());
			  }
		  }
		  String[] courseTitles= {"Selenium Webdriver Java", "jidsfsdf", "Protractor"};
		  
		  List<String> expectedListOfWebAutomationCourses =new ArrayList<>(Arrays.asList(courseTitles));
		  List<String> actualListOfWebAutomationCourses =new ArrayList<>();
		  for(int i=0;i<gc.getCourses().getWebAutomation().size();i++) {
			  actualListOfWebAutomationCourses.add(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
			  }
		  Assert.assertTrue(actualListOfWebAutomationCourses.equals(expectedListOfWebAutomationCourses));
		  
		  
	
	}
		  
		 
	}


