package com.ajahsma.carservice.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author SHARAN A
 */

@Entity(name = "Inventory")
@Table(name = "inventory")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InventoryTO extends AbstractIdDomain {
	
	private BigDecimal primaryApprovelAndEstimationTotal;
	private String fuelGuage;
	private String batteryMake;
	private String batteryNo;
	private String tyreMake;
	private String kmReading;
	private AttachmentTO carDamage;
//	private VehicleCustomerRegistration vehicleCustomerRegistration;
	private Set<ItemTO> items;
	private Set<PrimaryApprovelAndEstimationTO> primaryApprovelAndEstimations;
	private Set<CheckNomenclatureTO> checkNomenclatures;

	
	public InventoryTO(BigDecimal primaryApprovelAndEstimationTotal, String fuelGuage, String batteryMake, String batteryNo, String tyreMake, String kmReading) {
		super();
		this.primaryApprovelAndEstimationTotal = primaryApprovelAndEstimationTotal;
		this.fuelGuage = fuelGuage;
		this.batteryMake = batteryMake;
		this.batteryNo = batteryNo;
		this.tyreMake = tyreMake;
		this.kmReading = kmReading;
	}

	@Column(name="total", nullable=false)
	public BigDecimal getPrimaryApprovelAndEstimationTotal() {
		return primaryApprovelAndEstimationTotal;
	}

	public void setPrimaryApprovelAndEstimationTotal(BigDecimal primaryApprovelAndEstimationTotal) {
		this.primaryApprovelAndEstimationTotal = primaryApprovelAndEstimationTotal;
	}

	@Column(name="fuelguage", nullable=true)
	public String getFuelGuage() {
		return fuelGuage;
	}

	public void setFuelGuage(String fuelGuage) {
		this.fuelGuage = fuelGuage;
	}

	@Column(name="batterymake", nullable=true)
	public String getBatteryMake() {
		return batteryMake;
	}

	public void setBatteryMake(String batteryMake) {
		this.batteryMake = batteryMake;
	}

	@Column(name="batteryno", nullable=true)
	public String getBatteryNo() {
		return batteryNo;
	}

	public void setBatteryNo(String batteryNo) {
		this.batteryNo = batteryNo;
	}

	@Column(name="tyremake", nullable=true)
	public String getTyreMake() {
		return tyreMake;
	}

	public void setTyreMake(String tyreMake) {
		this.tyreMake = tyreMake;
	}

	@Column(name="kmreading", nullable=true)
	public String getKmReading() {
		return kmReading;
	}

	public void setKmReading(String kmReading) {
		this.kmReading = kmReading;
	}

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cardamage_id", nullable=true)
	public AttachmentTO getCarDamage() {
		return carDamage;
	}

	public void setCarDamage(AttachmentTO carDamage) {
		this.carDamage = carDamage;
	}

	/*@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name="vehcustregtion_id", nullable=false)
	public VehicleCustomerRegistration getVehicleCustomerRegistration() {
		return vehicleCustomerRegistration;
	}

	public void setVehicleCustomerRegistration(VehicleCustomerRegistration vehicleCustomerRegistration) {
		this.vehicleCustomerRegistration = vehicleCustomerRegistration;
	}*/

	@ManyToMany
	@JoinTable(name = "inventoryitem", joinColumns = { @JoinColumn(name = "inventory_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	public Set<ItemTO> getItems() {
		return items;
	}

	public void setItems(Set<ItemTO> items) {
		this.items = items;
	}

	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	public Set<PrimaryApprovelAndEstimationTO> getPrimaryApprovelAndEstimations() {
		return primaryApprovelAndEstimations;
	}

	public void setPrimaryApprovelAndEstimations(Set<PrimaryApprovelAndEstimationTO> primaryApprovelAndEstimations) {
		this.primaryApprovelAndEstimations = primaryApprovelAndEstimations;
	}

	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	public Set<CheckNomenclatureTO> getCheckNomenclatures() {
		return checkNomenclatures;
	}

	public void setCheckNomenclatures(Set<CheckNomenclatureTO> checkNomenclatures) {
		this.checkNomenclatures = checkNomenclatures;
	}
	
	public void addPrimaryApprovelAndEstimation(PrimaryApprovelAndEstimationTO primaryApprovelAndEstimation) {
		if(getPrimaryApprovelAndEstimations() == null) {
			setPrimaryApprovelAndEstimations(new HashSet<PrimaryApprovelAndEstimationTO>());
		}
		
		primaryApprovelAndEstimation.setInventory(this);
		getPrimaryApprovelAndEstimations().add(primaryApprovelAndEstimation);
	}

	public void addItem(ItemTO item) {
		if(getItems() == null) {
			setItems(new HashSet<ItemTO>());
		}
		
		getItems().add(item);
	}

	public void addCheckNomenclature(CheckNomenclatureTO checkNomenclature) {
		if(getCheckNomenclatures() == null) {
			setCheckNomenclatures(new HashSet<CheckNomenclatureTO>());
		}
		checkNomenclature.setInventory(this);
		getCheckNomenclatures().add(checkNomenclature);
	}
}
