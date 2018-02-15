package com.ajahsma.carservice.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.model.Domain;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CarServiceUtils {

	public static String convertObjectToJson(Domain domain) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    
		// Convert object to JSON string
		String jsonInString = mapper.writeValueAsString(domain);
		System.out.println(jsonInString);

		return jsonInString;
	}

	public static Domain convertJsonToObject(String jsonInString, Class clazz) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		// Convert object to JSON string and pretty print
		Domain domain = (Domain) mapper.readValue(jsonInString, clazz);
		return domain;
	}
	
	public static boolean isListNotNullAndEmpty(List list)
	{
		return (list != null && !list.isEmpty());
	}
	
	public static boolean isNull(Object object)
	{
		return object == null;
	}
	
	public static boolean isNotNull(Object object)
	{
		return object != null;
	}
	
	/**
	 * Return true if the supplied Collection is null or empty. Otherwise, return false.
	 */
	public static boolean isEmpty(Collection list)
	{
		return CollectionUtils.isEmpty(list);
	}
	
	/**
	 * Return true if the supplied Collection has atleast one value. Otherwise, return false.
	 */
	public static boolean isNotEmpty(Collection list)
	{
		return (!CollectionUtils.isEmpty(list));
	}
	
	public static <Source, Destination> Destination copyBeanProperties(Source source, Class Destination) throws InstantiationException, IllegalAccessException{
		Destination destination = (Destination) Destination.newInstance();
		BeanUtils.copyProperties(source, destination);
		return destination;
	}
	
	public static String createMessage(String message, String... placeHolders) {
		return MessageFormat.format(message, placeHolders);
	}

}
