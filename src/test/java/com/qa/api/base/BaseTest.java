package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import io.qameta.allure.restassured.AllureRestAssured;
import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;

//(ChainTestListener.class)
public class BaseTest {
	
	protected RestClient restClient;
	
	//*********API Base URLs********//
	protected  static  String BASE_URL_GOREST = null ;
	protected  static  String BASE_URL_OAUTH2_SPOTIFY = null ;
	protected  static  String BASE_URL_GET_ALBUM_SPOTIFY = null;
			
			//"https://gorest.co.in";
	protected final static  String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected final static  String BASE_BASIC_AUTH = "https://the-internet.herokuapp.com";
	protected final static  String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	


	//*********API EndPoints********//
	protected final static  String GOREST_USERS_ENDPOINT = "/public/v2/users";
	protected final static  String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected final static  String CONTACTS_ENDPOINT = "/contacts";
	protected final static  String BASIC_AUTH_ENDPOINT = "/basic_auth";
	protected final static  String PRODUCTS_ENDPOINT = "/products";
	protected final static  String OAUTH2_SPOTIFY_ENDPOINT = "/api/token";
	protected final static  String SPOTIFY_GET_ALBUM_DETAILS_ENDPOINT = "/v1/albums/4aawyAB9vmqN3uQ7FjRGTy";
	
	
		@BeforeSuite
		public void initSetup() {
			RestAssured.filters(new AllureRestAssured());
			BASE_URL_GOREST = ConfigManager.get("baseurl.gorest").trim();
			BASE_URL_OAUTH2_SPOTIFY = ConfigManager.get("baseurl.spotifyOauth2").trim();
			BASE_URL_GET_ALBUM_SPOTIFY = ConfigManager.get("baseurl.spotifyGetAlbum").trim();
			
		}
	  
	@BeforeTest
	public void setup() {
		restClient = new RestClient();		
	}

}
