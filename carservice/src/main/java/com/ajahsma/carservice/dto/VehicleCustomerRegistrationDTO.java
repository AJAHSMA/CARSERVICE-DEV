package com.ajahsma.carservice.dto;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DEVU I
 */

public class VehicleCustomerRegistrationDTO extends AbstractIdDomain {

	private Long distanceReading;
	private String jobCardNo;
	private Calendar deliveryDateTime;
	private Calendar currentDatetime;
//	private AttachmentDTO customerSignature;

	private VehicleDTO vehicle;
	private CustomerDTO customer;
	private InventoryDTO inventory;

	private Set<CustomerDemandedRepairDTO> customerDemandedRepairs;

	public VehicleCustomerRegistrationDTO(Long distanceReading, String jobCardNo, Calendar deliveryDateTime,
			Calendar currentDatetime, VehicleDTO vehicle, CustomerDTO customer, InventoryDTO inventory) {
		super();
		this.distanceReading = distanceReading;
		this.jobCardNo = jobCardNo;
		this.deliveryDateTime = deliveryDateTime;
		this.currentDatetime = currentDatetime;
		this.vehicle = vehicle;
		this.customer = customer;
		this.inventory = inventory;
	}

	public Long getDistanceReading() {
		return distanceReading;
	}

	public void setDistanceReading(Long distanceReading) {
		this.distanceReading = distanceReading;
	}

	public String getJobCardNo() {
		return jobCardNo;
	}

	public void setJobCardNo(String jobCardNo) {
		this.jobCardNo = jobCardNo;
	}

	public Calendar getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Calendar deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public Calendar getCurrentDatetime() {
		return currentDatetime;
	}

	public void setCurrentDatetime(Calendar currentDatetime) {
		this.currentDatetime = currentDatetime;
	}

	/*public AttachmentDTO getCustomerSignature() {
		return customerSignature;
	}

	public void setCustomerSignature(AttachmentDTO customerSignature) {
		this.customerSignature = customerSignature;
	}*/

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}

	/*
	 * 
	 * 
	 * 
	 * getInsideHood() { return insideHood; }
	 * 
	 * public void setInsideHood(InsideHood insideHood) { this.insideHood =
	 * insideHood; }
	 * 
	 * 
	 * 
	 * 
	 * getInsideCar() { return insideCar; }
	 * 
	 * public void setInsideCar(InsideCar insideCar) { this.insideCar =
	 * insideCar; }
	 * 
	 * 
	 * 
	 * 
	 * getOutsideCar() { return outsideCar; }
	 * 
	 * public void setOutsideCar(OutsideCar outsideCar) { this.outsideCar =
	 * outsideCar; }
	 * 
	 * 
	 * 
	 * 
	 * getUnclearBody() { return unclearBody; }
	 * 
	 * public void setUnclearBody(UnderBody unclearBody) { this.unclearBody =
	 * unclearBody; }
	 */

	public Set<CustomerDemandedRepairDTO> getCustomerDemandedRepairs() {
		return customerDemandedRepairs;
	}

	public void setCustomerDemandedRepairs(Set<CustomerDemandedRepairDTO> customerDemandedRepairs) {
		this.customerDemandedRepairs = customerDemandedRepairs;
	}

	public void addCustomerDemandedRepair(CustomerDemandedRepairDTO customerDemandedRepair) {
		if (getCustomerDemandedRepairs() == null) {
			setCustomerDemandedRepairs(new HashSet<CustomerDemandedRepairDTO>());
		}
		customerDemandedRepair.setVehicleCustomerRegistration(this);
		getCustomerDemandedRepairs().add(customerDemandedRepair);
	}

}
