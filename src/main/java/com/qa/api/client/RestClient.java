package com.qa.api.client;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Base64;
import java.util.Map;
import com.qa.api.manager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class RestClient {
	 private  ResponseSpecification responseSpec200 =expect().statusCode(200);
	 private  ResponseSpecification responseSpec201 =expect().statusCode(201);
	 private  ResponseSpecification responseSpec204 =expect().statusCode(204);
	 private  ResponseSpecification responseSpec401 =expect().statusCode(401);
	 private  ResponseSpecification responseSpec400 =expect().statusCode(400);
	 private  ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	 private  ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	 
	
	   private  RequestSpecification setUpRequest(String baseurl, AuthType authType, ContentType contentType) {

		RequestSpecification request = given().log().all()
				.baseUri(baseurl).contentType(contentType).accept(contentType);
		
		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearertoken"));
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic " + generateBasicAuthToken());
			break;
		case API_KEY:
			request.header("x-api-key", "Basic " + "api key");
			break;
		case NO_AUTH:
			System.out.println("Auth is not required here");
			break;

		default:
			System.out.println("This auth is not supported... please pass the right AuthType...");
			throw new APIException("===Invalid Auth====");

		}

		return request;

	}
	   
	   private String generateBasicAuthToken() {
		  String basicauthcredentials = ConfigManager.get("basicauthusername") + ":" + ConfigManager.get("basicauthpassword");
		  return Base64.getEncoder().encodeToString(basicauthcredentials.getBytes());
		  
	   }

	   

	   private void applyParams(RequestSpecification request, Map<String, String> queryParams,
			Map<String, String> pathParams) {

		if (queryParams!= null) {
			request.queryParams(queryParams);
		}
		if (pathParams!= null) {
			request.pathParams(pathParams);
		}
	}

//CRUD:: //GET:
	
	   /**
	    * This method is used to call Get APIs
	    * @param baseurl
	    * @param endPoint
	    * @param queryParams
	    * @param pathParams
	    * @param authType
	    * @param contentType
	    * @return This returns the GET api call response.
	    */
	  public Response get(String baseurl, String endPoint, Map<String, String>
	  queryParams, Map<String, String> pathParams, AuthType authType, ContentType
	  contentType) {
	  
	  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
	  applyParams(request, queryParams, pathParams); 
	  Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
	  response.prettyPrint(); 
	  return response;
 }
	  
	  // POST:
	  public <T>Response post (String baseurl, String endPoint, T body,
			            Map<String, String> queryParams, 
			            Map<String, String> pathParams, 
			            AuthType authType, 
			            ContentType contentType) {
		  
		  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
		  applyParams(request, queryParams, pathParams); 
		 Response response = request.body(body).post(endPoint).then().log().all()
				            .spec(responseSpec200or201).extract().response();
		 response.prettyPrint();
		 return response;
		  
  }
	  
	  
	  public Response post (String baseurl, String endPoint, String clientId, String clientSecret
			      , String grantType, ContentType contentType) {
		  Response response =  given()
				              .contentType(contentType)
				              .formParam("grant_type", grantType)
				              .formParam("client_secret", clientSecret)
				              .formParam("client_id", clientId)
				              .when()
				              .post(baseurl+endPoint);
		            
		  response.prettyPrint();
		  return response;

}
	  
	// POST:
		  public Response post (String baseurl, String endPoint, File file,
				            Map<String, String> queryParams, 
				            Map<String, String> pathParams, 
				            AuthType authType, 
				            ContentType contentType) {
			  
			  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
			  applyParams(request, queryParams, pathParams); 
			 Response response = request.body(file).post(endPoint).then().log().all()
					            .spec(responseSpec200or201).extract().response();
			 response.prettyPrint();
			 return response;
			  
	  }
	  
	  
		  // PUT:
		  public <T>Response put (String baseurl, String endPoint, T body,
				            Map<String, String> queryParams, 
				            Map<String, String> pathParams, 
				            AuthType authType, 
				            ContentType contentType) {
			  
			  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
			  applyParams(request, queryParams, pathParams); 
			 Response response = request.body(body).put(endPoint).then().log().all()
					            .spec(responseSpec200).extract().response();
			 response.prettyPrint();
			 return response;
			  
	  }
	  
		// PATCH:
		  public <T>Response patch (String baseurl, String endPoint, T body,
				            Map<String, String> queryParams, 
				            Map<String, String> pathParams, 
				            AuthType authType, 
				            ContentType contentType) {
			  
			  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
			  applyParams(request, queryParams, pathParams); 
			 Response response = request.body(body).patch(endPoint).then().log().all()
					            .spec(responseSpec200).extract().response();
			 response.prettyPrint();
			 return response;
			  
	  }
	  
		  
		// DELETE:
		  public <T>Response delete (String baseurl, String endPoint,
				            Map<String, String> queryParams, 
				            Map<String, String> pathParams, 
				            AuthType authType, 
				            ContentType contentType) {
			  
			  RequestSpecification request = setUpRequest(baseurl, authType, contentType);
			  applyParams(request, queryParams, pathParams); 
			 Response response = request.delete(endPoint).then().log().all()
					            .spec(responseSpec204).extract().response();
			 response.prettyPrint();
			 return response;
			  
	  }


}
