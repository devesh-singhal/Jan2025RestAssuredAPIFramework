package com.qa.api.utils;

import com.api.qa.gorest.tests.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {
	
	private static ObjectMapper objectMapper  = new ObjectMapper();
	public static <T> T deserialize(Response response, Class<T> class1) {
		
		
		try {
		 return  objectMapper.readValue(response.getBody().asString(), class1);
		
         }
	     catch (Exception e) {
	    	 throw new RuntimeException("deserialization is failed..." + class1.getName());
	    	 
	     }
		
	     }

}
