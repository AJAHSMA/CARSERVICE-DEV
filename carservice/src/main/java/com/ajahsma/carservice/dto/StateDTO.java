package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class StateDTO extends AbstractIdDomain {

	private String code;
	private String description;
	private CountryDTO country;

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

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

}
