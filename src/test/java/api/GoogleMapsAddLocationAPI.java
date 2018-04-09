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
import model.GoogleMapsAddLocationPOJO;

public class GoogleMapsAddLocationAPI extends Setup{

	protected static Response response;
	
	@Test(priority=1)
	public void addLocationOnMap() throws JsonParseException, JsonMappingException, IOException
	{
		GoogleMapsAddLocationPOJO request=mapper.readValue(new File(System.
				getProperty("user.dir")+"/src/test/resources/jsons/"
						+ "GoogleMapsAddLocationPayLoad.json"), GoogleMapsAddLocationPOJO.class);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request)
				+"\n\n"+"***********************************************************"+"\n\n");
		
		response=RestAssured.given().queryParam("key", prop.getProperty("key"))
				.body(request).when().post(prop.getProperty("addLocationResource")).then()
				.extract().response().prettyPeek();
		
		System.out.println("\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");	
		Assert.assertEquals(response.jsonPath().getString("status"), "OK");
	}
}
