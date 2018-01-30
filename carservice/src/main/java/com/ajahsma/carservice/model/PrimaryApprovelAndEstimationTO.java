package com.ajahsma.carservice.model;

import java.math.BigDecimal;

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

@Entity(name = "PrimaryApprovelAndEstimation")
@Table(name = "primaryapprovelestimation")
public class PrimaryApprovelAndEstimationTO extends AbstractIdDomain {
	
	public String description;
	public BigDecimal material;
	public BigDecimal labour;
	public BigDecimal total;
	public InventoryTO inventory;

	
	public PrimaryApprovelAndEstimationTO(String description, BigDecimal material, BigDecimal labour, BigDecimal total, InventoryTO inventory) {
		super();
		this.description = description;
		this.material = material;
		this.labour = labour;
		this.total = total;
		this.inventory = inventory;
	}

	@Column(name="description", nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="material", nullable=false)
	public BigDecimal getMaterial() {
		return material;
	}

	public void setMaterial(BigDecimal material) {
		this.material = material;
	}

	@Column(name="labour", nullable=false)
	public BigDecimal getLabour() {
		return labour;
	}

	public void setLabour(BigDecimal labour) {
		this.labour = labour;
	}

	@Column(name="total", nullable=false)
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "inventory_id", nullable = false)
	public InventoryTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryTO inventory) {
		this.inventory = inventory;
	}

}
