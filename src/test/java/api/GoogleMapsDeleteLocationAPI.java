package api;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import common.Setup;
import model.GoogleMapsDeleteLocationPOJO;

public class GoogleMapsDeleteLocationAPI extends Setup{
	
	private Response response;
	
	@Test(priority=2)
	public void deleteLocationFromMap() throws JsonParseException, JsonMappingException, IOException
	{	
		GoogleMapsDeleteLocationPOJO request=mapper.readValue(new File(
				System.getProperty("user.dir")+
				"/src/test/resources/jsons/GoogleMapsDeleteLocationPayLoad.json"), 
				GoogleMapsDeleteLocationPOJO.class);
				
		request.place_id=GoogleMapsAddLocationAPI.response.jsonPath().getString("place_id");
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request)
				+"\n\n"+"***********************************************************"+"\n\n");
		
		System.out.println(prop.toString());
		response=RestAssured.given().queryParam("key", prop.getProperty("key"))
				.body(request).when().post(prop.getProperty("deleteLocationResource")).then()
				.extract().response().prettyPeek();
		
		System.out.println("\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("status"), "OK");
	}
}
