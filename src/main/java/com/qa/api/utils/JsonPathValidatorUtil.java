package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {

	private static String getJsonResponseAsString(Response response) {
		return response.getBody().asString();

	}

	public static <T> T read(Response response, String jsonpath) {
		ReadContext ctx = JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonpath);
	}

	public static <T> List<T> readList(Response response, String jsonpath) {
      ReadContext ctx = JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonpath);
	}

	public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonpath) {
		ReadContext ctx = JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonpath);
	}

}
