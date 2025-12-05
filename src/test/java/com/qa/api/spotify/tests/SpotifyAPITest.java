package com.qa.api.spotify.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class SpotifyAPITest extends BaseTest {
		
	//private String accessToken; 
		
		@BeforeMethod
		public void getOAuth2Token() {
			  
	Response response = restClient.post(BASE_URL_OAUTH2_SPOTIFY, OAUTH2_SPOTIFY_ENDPOINT, 
				ConfigManager.get("clientid"), ConfigManager.get("clientsecret"), ConfigManager.get("granttype")
				,ContentType.URLENC);
	
	   String  accessToken =  response.jsonPath().getString("access_token");
	     System.out.println("Access token fetched is =="+ accessToken);
	     ConfigManager.set("bearertoken", accessToken);
		
	}
		 @Test
		 public void getAlbumTest() {
			 
	Response response = restClient.get(BASE_URL_GET_ALBUM_SPOTIFY, SPOTIFY_GET_ALBUM_DETAILS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	  String responsebody = response.getBody().asString();
	  System.out.println(responsebody);
	Assert.assertEquals(response.getStatusCode(),200);
	
	
	
	
			 
			 
			 
		 }
		
		
	
}
