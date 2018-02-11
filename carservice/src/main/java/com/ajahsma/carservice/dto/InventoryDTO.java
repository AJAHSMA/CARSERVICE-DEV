package com.ajahsma.carservice.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DEVU I
 */

public class InventoryDTO extends AbstractIdDomain {

	private BigDecimal primaryApprovelAndEstimationTotal;
	private String fuelGuage;
	private String batteryMake;
	private String batteryNo;
	private String tyreMake;
	private String kmReading;
	private AttachmentDTO carDamage;
	// private VehicleCustomerRegistration vehicleCustomerRegistration;
	private Set<ItemDTO> items;
	private Set<PrimaryApprovelAndEstimationDTO> primaryApprovelAndEstimations;
	private Set<CheckNomenclatureDTO> checkNomenclatures;

	public InventoryDTO(BigDecimal primaryApprovelAndEstimationTotal, String fuelGuage, String batteryMake,
			String batteryNo, String tyreMake, String kmReading) {
		super();
		this.primaryApprovelAndEstimationTotal = primaryApprovelAndEstimationTotal;
		this.fuelGuage = fuelGuage;
		this.batteryMake = batteryMake;
		this.batteryNo = batteryNo;
		this.tyreMake = tyreMake;
		this.kmReading = kmReading;
	}

	public BigDecimal getPrimaryApprovelAndEstimationTotal() {
		return primaryApprovelAndEstimationTotal;
	}

	public void setPrimaryApprovelAndEstimationTotal(BigDecimal primaryApprovelAndEstimationTotal) {
		this.primaryApprovelAndEstimationTotal = primaryApprovelAndEstimationTotal;
	}

	public String getFuelGuage() {
		return fuelGuage;
	}

	public void setFuelGuage(String fuelGuage) {
		this.fuelGuage = fuelGuage;
	}

	public String getBatteryMake() {
		return batteryMake;
	}

	public void setBatteryMake(String batteryMake) {
		this.batteryMake = batteryMake;
	}

	public String getBatteryNo() {
		return batteryNo;
	}

	public void setBatteryNo(String batteryNo) {
		this.batteryNo = batteryNo;
	}

	public String getTyreMake() {
		return tyreMake;
	}

	public void setTyreMake(String tyreMake) {
		this.tyreMake = tyreMake;
	}

	public String getKmReading() {
		return kmReading;
	}

	public void setKmReading(String kmReading) {
		this.kmReading = kmReading;
	}

	public AttachmentDTO getCarDamage() {
		return carDamage;
	}

	public void setCarDamage(AttachmentDTO carDamage) {
		this.carDamage = carDamage;
	}

	/*
	 * 
	 * public VehicleCustomerRegistration getVehicleCustomerRegistration() {
	 * return vehicleCustomerRegistration; }
	 * 
	 * public void setVehicleCustomerRegistration(VehicleCustomerRegistration
	 * vehicleCustomerRegistration) { this.vehicleCustomerRegistration =
	 * vehicleCustomerRegistration; }
	 */

	public Set<ItemDTO> getItems() {
		return items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;
	}

	public Set<PrimaryApprovelAndEstimationDTO> getPrimaryApprovelAndEstimations() {
		return primaryApprovelAndEstimations;
	}

	public void setPrimaryApprovelAndEstimations(Set<PrimaryApprovelAndEstimationDTO> primaryApprovelAndEstimations) {
		this.primaryApprovelAndEstimations = primaryApprovelAndEstimations;
	}

	public Set<CheckNomenclatureDTO> getCheckNomenclatures() {
		return checkNomenclatures;
	}

	public void setCheckNomenclatures(Set<CheckNomenclatureDTO> checkNomenclatures) {
		this.checkNomenclatures = checkNomenclatures;
	}

	public void addPrimaryApprovelAndEstimation(PrimaryApprovelAndEstimationDTO primaryApprovelAndEstimation) {
		if (getPrimaryApprovelAndEstimations() == null) {
			setPrimaryApprovelAndEstimations(new HashSet<PrimaryApprovelAndEstimationDTO>());
		}

		primaryApprovelAndEstimation.setInventory(this);
		getPrimaryApprovelAndEstimations().add(primaryApprovelAndEstimation);
	}

	public void addItem(ItemDTO item) {
		if (getItems() == null) {
			setItems(new HashSet<ItemDTO>());
		}

		getItems().add(item);
	}

	public void addCheckNomenclature(CheckNomenclatureDTO checkNomenclature) {
		if (getCheckNomenclatures() == null) {
			setCheckNomenclatures(new HashSet<CheckNomenclatureDTO>());
		}
		checkNomenclature.setInventory(this);
		getCheckNomenclatures().add(checkNomenclature);
	}
}
