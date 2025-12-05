package com.qa.api.utils;

import java.util.List;
import java.util.Map;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlpathUtil {
	
	public class xmlPathValidatorUtil {

		private XmlPath getXmlPath(Response response) {
			String responseBody = response.getBody().asString();
			return new XmlPath(responseBody);

		}

		public  <T> T read(Response response, String xmlPathExpression) {
			XmlPath xmlPath = getXmlPath(response);
			return xmlPath.get(xmlPathExpression);
		}

		public  <T> List<T> readList(Response response, String xmlPathExpression) {
			XmlPath xmlPath = getXmlPath(response);
			return xmlPath.get(xmlPathExpression);
		}

		public  <T> List<Map<String, T>> readListOfMaps(Response response, String xmlPathExpression) {
			XmlPath xmlPath = getXmlPath(response);
			return xmlPath.getList(xmlPathExpression);
		}

	}


}
