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

@Entity(name = "CarModel")
@Table(name = "carmodel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CarModelTO extends AbstractIdDomain {
	
	private CarMakeTO carMake;
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name = "carmake_id", nullable = false)
	public CarMakeTO getCarMake() {
		return carMake;
	}

	public void setCarMake(CarMakeTO carMake) {
		this.carMake = carMake;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
