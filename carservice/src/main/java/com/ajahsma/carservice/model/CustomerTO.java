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

@Entity(name = "Customer")
@Table(name = "customer")
public class CustomerTO extends AbstractIdDomain {
	
	private String name;
	private String phoneNo;
	private String emailId;
	private String address;
	private CityTO city;
	private String pincode;
	private String idProof;
	private String idProofNo;

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="phoneno", nullable=false)
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name="emailid", nullable=true)
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name="address", nullable=false)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "city_id", nullable = false)
	public CityTO getCity() {
		return city;
	}

	public void setCity(CityTO city) {
		this.city = city;
	}
	
	@Column(name="pincode", nullable=false)
	public String getPincode() {
		return pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Column(name="idproof", nullable=false)
	public String getIdProof() {
		return idProof;
	}
	
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	
	@Column(name="idproofno", nullable=false)
	public String getIdProofNo() {
		return idProofNo;
	}
	
	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}
	
}
