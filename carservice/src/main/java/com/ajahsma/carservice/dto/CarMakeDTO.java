package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class CarMakeDTO extends AbstractIdDomain {

	private String code;
	private String name;

	public String getCode() {
		System.out.println("dev");
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
