package com.api.qa.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPath extends BaseTest{
	
	@Test
	public void getProductTest() {
		Response response =	restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 200);
	
            
		/*
		 * List <Number> prices= JsonPathValidatorUtil.readList(response,
		 * "$[?(@.price>50)].price"); System.out.println(prices);
		 * 
		 * List <Number> ids = JsonPathValidatorUtil.readList(response,
		 * "$[?(@.price>50)].id"); System.out.println(ids);
		 * 
		 * List <Number> rates = JsonPathValidatorUtil.readList(response,
		 * "$[?(@.price>50)].rating.rate"); System.out.println(rates);
		 * 
		 * List <Number> counts = JsonPathValidatorUtil.readList(response,
		 * "$[?(@.price>50)].rating.count"); System.out.println(counts);
		 */
	
	List<Map<String, Object>> idTitleList = JsonPathValidatorUtil.readListOfMaps(response, "$.[*].['id', 'title']");
          for (Map<String, Object> e : idTitleList ) {
        	       int id =   (int) e.get("id");
        	      String title = (String) e.get("title");
        	      System.out.println(id);
        	      System.out.println(title);
          }
	
	}
	

}










