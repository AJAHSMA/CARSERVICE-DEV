package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class CityDTO extends AbstractIdDomain {

	private String code;
	private String description;
	private StateDTO state;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}

}
