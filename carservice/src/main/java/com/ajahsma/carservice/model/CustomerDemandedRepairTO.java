package com.ajahsma.carservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author SHARAN A
 */

@Entity(name = "CustomerDemandedRepair")
@Table(name = "customerdemandedrepair")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerDemandedRepairTO extends AbstractIdDomain {
	
	private String description;
	private VehicleCustomerRegistrationTO vehicleCustomerRegistration;

	@Column(name="description", nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "vehcustregtion_id", nullable = false)
	public VehicleCustomerRegistrationTO getVehicleCustomerRegistration() {
		return vehicleCustomerRegistration;
	}

	public void setVehicleCustomerRegistration(VehicleCustomerRegistrationTO vehicleCustomerRegistration) {
		this.vehicleCustomerRegistration = vehicleCustomerRegistration;
	}
	

}
