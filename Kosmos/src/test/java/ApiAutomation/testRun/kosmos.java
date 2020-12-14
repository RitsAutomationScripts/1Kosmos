package ApiAutomation.testRun;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class kosmos {
		
	@Test
	public void Testcase(){
		RestAssured.baseURI = "https://1k-dev.1kosmos.net";
		RestAssured.given().get("/healthz").then().statusCode(200).log().all();		
	}
	
	@Test
	public void TestcaseEULA(){
		RequestSpecification requestspecification = RestAssured.given();
		Response response = requestspecification.contentType(ContentType.JSON).request(Method.GET,"/api/v3/rest/default/eula?tenant=1kosmos");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, 200,"Response code is not Ok");
		
		String eula_b64 = response.then().extract().path("eula_b64");
		System.out.println("eula_b64="+eula_b64);	
		
		
	}
}