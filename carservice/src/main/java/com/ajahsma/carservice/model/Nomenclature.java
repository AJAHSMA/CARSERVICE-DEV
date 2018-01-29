package com.ajahsma.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractIdDomain {
	
	private String code;
	private String name;
	private String checkType;

	@Column(name="code", nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="checkTypeenum", nullable=false)
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	

}
