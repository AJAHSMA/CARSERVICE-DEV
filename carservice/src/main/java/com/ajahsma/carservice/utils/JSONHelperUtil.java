package com.ajahsma.carservice.utils;

import com.ajahsma.carservice.json.Data;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.Jsonaapi;

public class JSONHelperUtil {

	synchronized public static JsonResponse getJsonResponse(String version, String type, Object items) {
		
		Data data = new Data();
		data.setItems(items);
		data.setType(type);
		Jsonaapi jsonaapi = new Jsonaapi();
		jsonaapi.setVersion(version);
		JsonResponse jsonResponse = new JsonResponse(jsonaapi, data);
		return jsonResponse;

	}

}
