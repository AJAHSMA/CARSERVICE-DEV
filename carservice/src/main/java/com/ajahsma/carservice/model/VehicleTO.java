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

@SuppressWarnings("serial")
@Entity(name = "Vehicle")
@Table(name = "vehicle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehicleTO extends AbstractIdDomain {
	
	private String registerNo;
	private String engineNo;
	private String chessisNo;
//	private Make make ;
	private CarModelTO carModel;
	private String fuel;

	@Column(name="registerno", nullable=false)
	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	@Column(name="engineno", nullable=true)
	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	@Column(name="chessisno", nullable=true)
	public String getChessisNo() {
		return chessisNo;
	}

	public void setChessisNo(String chessisNo) {
		this.chessisNo = chessisNo;
	}

	@Column(name="fuel", nullable=true)
	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	/*@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "make_id", nullable = true)
	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}*/

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "carmodel_id", nullable = true)
	public CarModelTO getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModelTO carModel) {
		this.carModel = carModel;
	}
	
}
