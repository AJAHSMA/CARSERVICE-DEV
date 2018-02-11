package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

@SuppressWarnings("serial")

public class VehicleDTO extends AbstractIdDomain {

	private String registerNo;
	private String engineNo;
	private String chessisNo;
	// private Make make ;
	private CarModelDTO carModel;
	private String fuel;

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getChessisNo() {
		return chessisNo;
	}

	public void setChessisNo(String chessisNo) {
		this.chessisNo = chessisNo;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	/*
	 * 
	 * public Make getMake() { return make; }
	 * 
	 * public void setMake(Make make) { this.make = make; }
	 */

	public CarModelDTO getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModelDTO carModel) {
		this.carModel = carModel;
	}

}
