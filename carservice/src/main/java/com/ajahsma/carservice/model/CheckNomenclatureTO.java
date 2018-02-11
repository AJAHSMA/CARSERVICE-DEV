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

@Entity(name = "CheckNomenclature")
@Table(name = "checknomenclature")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CheckNomenclatureTO extends AbstractIdDomain {
	
	private NomenclatureTO nomenclature;
	private Boolean isCheck;
	private String remarks;
	public InventoryTO inventory;
	
	public CheckNomenclatureTO(NomenclatureTO nomenclature, Boolean isCheck, String remarks) {
		super();
		this.nomenclature = nomenclature;
		this.isCheck = isCheck;
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "nomenclature_id", nullable = false)
	public NomenclatureTO getNomenclature() {
		return nomenclature;
	}
	
	public void setNomenclature(NomenclatureTO nomenclature) {
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

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "inventory_id", nullable = false)
	public InventoryTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryTO inventory) {
		this.inventory = inventory;
	}
}
