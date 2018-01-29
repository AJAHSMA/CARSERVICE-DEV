package com.ajahsma.carservice.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity
@Table(name = "vehcustregtion")
public class VehicleCustomerRegistration extends AbstractIdDomain {

	private Long distanceReading;
	private String jobCardNo;
	private Calendar deliveryDateTime;
	private Calendar currentDatetime;
	private Attachment customerSignature;

	private Vehicle vehicle;
	private Customer customer;
	private Inventory inventory;
	// private InsideHood insideHood;
	// private InsideCar insideCar;
	// private OutsideCar outsideCar;
	// private UnderBody unclearBody;

	private Set<CustomerDemandedRepair> customerDemandedRepairs;

	public VehicleCustomerRegistration(Long distanceReading, String jobCardNo, Calendar deliveryDateTime, Calendar currentDatetime, Vehicle vehicle, Customer customer, Inventory inventory) {
		super();
		this.distanceReading = distanceReading;
		this.jobCardNo = jobCardNo;
		this.deliveryDateTime = deliveryDateTime;
		this.currentDatetime = currentDatetime;
		this.vehicle = vehicle;
		this.customer = customer;
		this.inventory = inventory;
	}

	@Column(name = "distancereading", nullable = false)
	public Long getDistanceReading() {
		return distanceReading;
	}

	public void setDistanceReading(Long distanceReading) {
		this.distanceReading = distanceReading;
	}

	@Column(name = "jobcardno", nullable = true)
	public String getJobCardNo() {
		return jobCardNo;
	}

	public void setJobCardNo(String jobCardNo) {
		this.jobCardNo = jobCardNo;
	}

	@Column(name = "deliverydatetime", nullable = true)
	public Calendar getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Calendar deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	@Column(name = "currentdatetime", nullable = false)
	public Calendar getCurrentDatetime() {
		return currentDatetime;
	}

	public void setCurrentDatetime(Calendar currentDatetime) {
		this.currentDatetime = currentDatetime;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customersignature_id", nullable = true)
	public Attachment getCustomerSignature() {
		return customerSignature;
	}

	public void setCustomerSignature(Attachment customerSignature) {
		this.customerSignature = customerSignature;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "vehicle_id", nullable = false)
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id", nullable = true)
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/*
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="insidehood_id", nullable=true) public InsideHood
	 * getInsideHood() { return insideHood; }
	 * 
	 * public void setInsideHood(InsideHood insideHood) { this.insideHood =
	 * insideHood; }
	 * 
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="insidecar_id", nullable=true) public InsideCar
	 * getInsideCar() { return insideCar; }
	 * 
	 * public void setInsideCar(InsideCar insideCar) { this.insideCar =
	 * insideCar; }
	 * 
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="outsidecar_id", nullable=true) public OutsideCar
	 * getOutsideCar() { return outsideCar; }
	 * 
	 * public void setOutsideCar(OutsideCar outsideCar) { this.outsideCar =
	 * outsideCar; }
	 * 
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="unclearbody_id", nullable=true) public UnderBody
	 * getUnclearBody() { return unclearBody; }
	 * 
	 * public void setUnclearBody(UnderBody unclearBody) { this.unclearBody =
	 * unclearBody; }
	 */

	@OneToMany(mappedBy = "vehicleCustomerRegistration", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	public Set<CustomerDemandedRepair> getCustomerDemandedRepairs() {
		return customerDemandedRepairs;
	}

	public void setCustomerDemandedRepairs(Set<CustomerDemandedRepair> customerDemandedRepairs) {
		this.customerDemandedRepairs = customerDemandedRepairs;
	}

	public void addCustomerDemandedRepair(CustomerDemandedRepair customerDemandedRepair) {
		if(getCustomerDemandedRepairs() == null) {
			setCustomerDemandedRepairs(new HashSet<CustomerDemandedRepair>());
		}
		customerDemandedRepair.setVehicleCustomerRegistration(this);
		getCustomerDemandedRepairs().add(customerDemandedRepair);
	}

}
