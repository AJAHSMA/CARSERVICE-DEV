package com.ajahsma.carservice.dto;

import java.math.BigDecimal;

/**
 * @author DEVU I
 */

public class PrimaryApprovelAndEstimationDTO extends AbstractIdDomain {

	public String description;
	public BigDecimal material;
	public BigDecimal labour;
	public BigDecimal total;
	public InventoryDTO inventory;

	public PrimaryApprovelAndEstimationDTO(String description, BigDecimal material, BigDecimal labour, BigDecimal total,
			InventoryDTO inventory) {
		super();
		this.description = description;
		this.material = material;
		this.labour = labour;
		this.total = total;
		this.inventory = inventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMaterial() {
		return material;
	}

	public void setMaterial(BigDecimal material) {
		this.material = material;
	}

	public BigDecimal getLabour() {
		return labour;
	}

	public void setLabour(BigDecimal labour) {
		this.labour = labour;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}

}
