package com.ajahsma.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity(name = "Parameter")
@Table(name = "parameter")
public class ParameterTO extends AbstractIdDomain {
	
	private String applicationId;
	private String value;

	@Column(name="applicationid", nullable=false)
	public String getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	@Column(name="value", nullable=false)
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	

}
