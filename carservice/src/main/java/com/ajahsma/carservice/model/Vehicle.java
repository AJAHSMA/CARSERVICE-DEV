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
@Table(name = "vehicle")
public class Vehicle extends AbstractIdDomain {
	
	private String registerNo;
	private String engineNo;
	private String chessisNo;
//	private Make make ;
	private Model model;
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

	/*@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "make_id", nullable = true)
	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}*/

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "model_id", nullable = true)
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
