package com.ajahsma.carservice.dto;

import java.util.Calendar;

/**
 * @author DEVU I
 */

public class EmployeeDTO extends AbstractIdDomain {

	private String tittle;
	private String applicationId;
	private String name;
	private String gardianName;
	private String address;
	private String pincode;
	private String permanentAaddress;
	private String permanentPincode;
	private String phoneNo;
	private String emailId;
	private String idProof;
	private String idProofNo;
	private String officePhoneNo;
	private String officeEmailId;
	// private String password;

	private Boolean isActive;

	private Calendar dob;
	private Calendar joingDate;

	private CityDTO city;
	// private State state;
	// private Country country;
	private DesignationDTO designation;

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGardianName() {
		return gardianName;
	}

	public void setGardianName(String gardianName) {
		this.gardianName = gardianName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPermanentAaddress() {
		return permanentAaddress;
	}

	public void setPermanentAaddress(String permanentAaddress) {
		this.permanentAaddress = permanentAaddress;
	}

	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getIdProofNo() {
		return idProofNo;
	}

	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}

	public String getOfficePhoneNo() {
		return officePhoneNo;
	}

	public void setOfficePhoneNo(String officePhoneNo) {
		this.officePhoneNo = officePhoneNo;
	}

	public String getOfficeEmailId() {
		return officeEmailId;
	}

	public void setOfficeEmailId(String officeEmailId) {
		this.officeEmailId = officeEmailId;
	}

	/*
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 */

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public Calendar getJoingDate() {
		return joingDate;
	}

	public void setJoingDate(Calendar joingDate) {
		this.joingDate = joingDate;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	/*
	 * 
	 * public State getState() { return state; }
	 * 
	 * public void setState(State state) { this.state = state; }
	 * 
	 * 
	 * 
	 * public Country getCountry() { return country; }
	 * 
	 * public void setCountry(Country country) { this.country = country; }
	 */

	public DesignationDTO getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationDTO designation) {
		this.designation = designation;
	}

}
