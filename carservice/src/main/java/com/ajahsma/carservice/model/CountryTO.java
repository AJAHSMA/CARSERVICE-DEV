package com.ajahsma.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author SHARAN A
 */

@Entity(name = "Country")
@Table(name = "country")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountryTO extends AbstractIdDomain {
	
	private String code;

	private String description;

	@Column(name="code", nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="description", nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
