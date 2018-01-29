package com.ajahsma.carservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity
@Table(name = "customerdemandedrepair")
public class CustomerDemandedRepair extends AbstractIdDomain {
	
	private String description;
	private VehicleCustomerRegistration vehicleCustomerRegistration;

	@Column(name="description", nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "vehcustregtion_id", nullable = false)
	public VehicleCustomerRegistration getVehicleCustomerRegistration() {
		return vehicleCustomerRegistration;
	}

	public void setVehicleCustomerRegistration(VehicleCustomerRegistration vehicleCustomerRegistration) {
		this.vehicleCustomerRegistration = vehicleCustomerRegistration;
	}
	

}
