package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class NomenclatureDTO extends AbstractIdDomain {

	private String code;
	private String name;
	private String checkType;

	public String getCode() {
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

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

}
