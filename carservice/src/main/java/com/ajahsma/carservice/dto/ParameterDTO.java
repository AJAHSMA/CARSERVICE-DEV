package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class ParameterDTO extends AbstractIdDomain {

	private String applicationId;
	private String value;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
