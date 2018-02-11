package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class CustomerDemandedRepairDTO extends AbstractIdDomain {

	private String description;
	private VehicleCustomerRegistrationDTO vehicleCustomerRegistration;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VehicleCustomerRegistrationDTO getVehicleCustomerRegistration() {
		return vehicleCustomerRegistration;
	}

	public void setVehicleCustomerRegistration(VehicleCustomerRegistrationDTO vehicleCustomerRegistration) {
		this.vehicleCustomerRegistration = vehicleCustomerRegistration;
	}

}
