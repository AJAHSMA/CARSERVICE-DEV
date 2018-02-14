package com.ajahsma.carservice.json;

public class JsonResponse {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String DATA = "date";
	
	public JsonResponse(Jsonaapi jsonaapi, Data data) {
		super();
		this.jsonaapi = jsonaapi;
		this.data = data;
	}

	private Jsonaapi jsonaapi;

	private Data data;

	public Jsonaapi getJsonaapi() {
		return jsonaapi;
	}

	public void setJsonaapi(Jsonaapi jsonaapi) {
		this.jsonaapi = jsonaapi;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ClassPojo [jsonaapi = " + jsonaapi + ", data = " + data + "]";
	}
}
