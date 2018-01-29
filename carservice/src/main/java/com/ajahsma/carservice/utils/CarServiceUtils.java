package com.ajahsma.carservice.utils;

import java.io.IOException;

import com.ajahsma.carservice.model.Domain;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarServiceUtils {

	public static String convertObjectToJson(Domain domain) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		// Convert object to JSON string
		String jsonInString = mapper.writeValueAsString(domain);
		System.out.println(jsonInString);

		return jsonInString;
	}

	public static Domain convertJsonToObject(String jsonInString, Class clazz) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		// Convert object to JSON string and pretty print
		Domain domain = (Domain) mapper.readValue(jsonInString, clazz);
		return domain;
	}

}
