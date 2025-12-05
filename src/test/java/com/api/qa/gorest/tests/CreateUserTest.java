package com.api.qa.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserLombok;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	
	
	  private String tokenId;
	  
	  @BeforeClass public void setUpToken() { tokenId =
	  "f4d6f854c93a235616c4cbf2fc4ecd38684edabc8d88a2aac53a85e802632170";
	  ConfigManager.set("bearertoken", tokenId); 
	  }
	 
	
	 
	/*
	 * @DataProvider public Object[][] getUserData(){ return new Object [][] {
	 * {"priyanka", "female", "active"}, {"anuj", "male", "active"}, {"yogesh",
	 * "male", "active"} };
	 * 
	 * }
	 */
	
	@DataProvider
	public Object[] [] getUserExcelData() {
		  return ExcelUtil.readCreateUserData(AppConstants.CREATE_USER_SHEET_NAME);
		
	} 
	
	 
	@Test (dataProvider = "getUserExcelData")
	public void createAUserTest(String name, String gender, String status) {

	UserLombok user = new UserLombok(null, name, StringUtils.getRandomEmailId(), gender , status);
			Response response =  restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertEquals(response.jsonPath().getString("status"), status);
		
		
	}
	
	
	@Test
	public void createAUserTestWithJsonFile() {
		
		File file = new File ("./src/test/resources/jsons/user.json");
		
		Response response =  restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, file, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Radhika");
		
		
	}
	
	
	
	
	
	
	
	
	

}
