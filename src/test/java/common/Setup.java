package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.http.ContentType;

public class Setup {

	private FileInputStream fis;
	protected static Properties prop;
	protected static ObjectMapper mapper;
	
	@BeforeSuite
	public void defaultSetup() throws IOException
	{
		RestAssured.useRelaxedHTTPSValidation();
		
		RestAssured.config=RestAssured.config().encoderConfig(EncoderConfig.encoderConfig()
				.appendDefaultContentCharsetToContentTypeIfUndefined(false)
				.encodeContentTypeAs("application-json", ContentType.JSON));
		
		fis=new FileInputStream(System.getProperty("user.dir")+"//config.properties");
		prop=new Properties();
		prop.load(fis);
		
		mapper=new ObjectMapper();
	}
	
	@BeforeTest
	public void setupEndPoint()
	{
		RestAssured.baseURI=prop.getProperty("googleMapURL");
	}
}
