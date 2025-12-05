package com.api.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAUserTestWithDeserialization extends BaseTest {
	
private String tokenId;
	
	@BeforeClass
	 public void setUpToken() {
		tokenId = "f4d6f854c93a235616c4cbf2fc4ecd38684edabc8d88a2aac53a85e802632170";
		ConfigManager.set("bearertoken", tokenId);
	}
	
	 
	 
	@Test
	public void createAUserTest() {
		
		
		UserLombok user = new UserLombok(null, "Priyanka", StringUtils.getRandomEmailId(), "female" , "active");
		Response response =  restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Priyanka");
		Assert.assertNotNull(response.jsonPath().getString("id"));
		
	   String  UserID = response.jsonPath().getString("id"); 
	    Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+UserID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	    	Assert.assertTrue(responseGet.statusLine().contains("OK"));
	    	
	        UserLombok userResponse =  JsonUtils.deserialize(responseGet, UserLombok.class);	
	        Assert.assertEquals(userResponse.getName(), user.getName());
	    }
		
		
		
		
	}

