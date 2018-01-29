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
@Table(name = "checknomenclature")
public class CheckNomenclature extends AbstractIdDomain {
	
	private Nomenclature nomenclature;
	private Boolean isCheck;
	private String remarks;
	public Inventory inventory;
	
	public CheckNomenclature(Nomenclature nomenclature, Boolean isCheck, String remarks) {
		super();
		this.nomenclature = nomenclature;
		this.isCheck = isCheck;
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "nomenclature_id", nullable = false)
	public Nomenclature getNomenclature() {
		return nomenclature;
	}
	
	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}
	
	@Column(name="ischeck", nullable=false)
	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	@Column(name="remarks", nullable=false)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "inventory_id", nullable = false)
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
