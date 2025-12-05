package com.api.qa.products.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest{

	@Test
	public void getAllProducts() {
	Response response =	restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
	Assert.assertEquals(response.getStatusCode(), 200);
	
	     Product[] product =   JsonUtils.deserialize(response, Product[].class);
	
	
	}
}
