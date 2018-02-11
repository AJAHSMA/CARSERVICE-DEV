package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class CheckNomenclatureDTO extends AbstractIdDomain {

	private NomenclatureDTO nomenclature;
	private Boolean isCheck;
	private String remarks;
	public InventoryDTO inventory;

	public CheckNomenclatureDTO(NomenclatureDTO nomenclature, Boolean isCheck, String remarks) {
		super();
		this.nomenclature = nomenclature;
		this.isCheck = isCheck;
		this.remarks = remarks;
	}

	public NomenclatureDTO getNomenclature() {
		return nomenclature;
	}

	public void setNomenclature(NomenclatureDTO nomenclature) {
		this.nomenclature = nomenclature;
	}

	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}
}
