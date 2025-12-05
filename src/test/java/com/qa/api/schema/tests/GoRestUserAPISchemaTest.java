package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserAPISchemaTest extends BaseTest{

	@Test
	public void getUserAPISchemaTest() {
		
		
ConfigManager.set("bearertoken", "f4d6f854c93a235616c4cbf2fc4ecd38684edabc8d88a2aac53a85e802632170");
Response response =restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	
Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/getuserschema.json"));
}
	
	@Test
	public void createUserAPISchemaTest() {
		ConfigManager.set("bearertoken", "f4d6f854c93a235616c4cbf2fc4ecd38684edabc8d88a2aac53a85e802632170");
	 	UserLombok user =  UserLombok.builder()
		                   .name("apiusername")
		                         .email(StringUtils.getRandomEmailId())
		                          .gender("male")
		                             .status("active").build();
	 	
	 	Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	 	Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/createuserschema.json"));
	
	
	
	
	
	
	}
		

}
