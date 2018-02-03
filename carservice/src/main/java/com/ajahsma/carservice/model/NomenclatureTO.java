package com.ajahsma.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author SHARAN A
 */

@Entity(name = "Nomenclature")
@Table(name = "nomenclature")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NomenclatureTO extends AbstractIdDomain {
	
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
