package com.api.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest  extends BaseTest  {
	
	private String tokenId;
	  
	  @BeforeClass public void setUpToken() { 
		  tokenId = "f4d6f854c93a235616c4cbf2fc4ecd38684edabc8d88a2aac53a85e802632170";
	  ConfigManager.set("bearertoken", tokenId); 
	  }
	  
	@Test
	public void updateUserTest(){
  UserLombok user = UserLombok.builder()
				        .name("pashika")
				        .email(StringUtils.getRandomEmailId())
				        .gender("female")
				        .status("active").build();
		
Response responsePost =  restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN , ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("name"), "pashika");
		Assert.assertEquals(responsePost.jsonPath().getString("gender"), "female");
		String userId = responsePost.jsonPath().getString("id");
		System.out.println("user id =====>" + userId);
		                    
		
		// GET : get the same user fetching the same userid 
Response responseGet =  restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
		
		
		//Update API" update the user using the same user id:
		user.setName("vishnoi");
		user.setStatus("inactive");
		Response responsePut =  restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.jsonPath().getString("name"), "vishnoi");
		Assert.assertEquals(responsePut.jsonPath().getString("status"), "inactive");
		Assert.assertEquals (responsePut.jsonPath().getString("id"), userId);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
