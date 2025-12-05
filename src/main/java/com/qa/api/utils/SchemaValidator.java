package com.qa.api.utils;

import io.restassured.response.Response;
import io.restassured.module.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidator {
	
	public static boolean validateSchema(Response response, String schemaFileName) {
		try {
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
		System.out.println("schema validation is passed..." +schemaFileName);
		return true;
		}
		catch(Exception e) {
			System.out.println("schema validation is failed..." +e.getMessage());
			return false;
			
		}
	}

}
