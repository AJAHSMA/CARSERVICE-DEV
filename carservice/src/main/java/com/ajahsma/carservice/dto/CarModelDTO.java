package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class CarModelDTO extends AbstractIdDomain {

	private CarMakeDTO carMake;
	private String name;

	public CarMakeDTO getCarMake() {
		return carMake;
	}

	public void setCarMake(CarMakeDTO carMake) {
		this.carMake = carMake;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
