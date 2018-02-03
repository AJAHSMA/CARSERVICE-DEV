package com.ajahsma.carservice.model;

import java.util.Calendar;

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

@Entity(name = "Employee")
@Table(name = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeTO extends AbstractIdDomain {
	
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
//	private String password;
	
	private Boolean isActive;
	
	private Calendar dob;
	private Calendar joingDate;
	
	private CityTO city;
//	private State state;
//	private Country country;
	private DesignationTO designation;

	@Column(name="tittle", nullable=false)
	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	@Column(name="applicationid", nullable=false)
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="gardianname", nullable=false)
	public String getGardianName() {
		return gardianName;
	}

	public void setGardianName(String gardianName) {
		this.gardianName = gardianName;
	}

	@Column(name="address", nullable=false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="pincode", nullable=false)
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Column(name="permanentaddress", nullable=false)
	public String getPermanentAaddress() {
		return permanentAaddress;
	}

	public void setPermanentAaddress(String permanentAaddress) {
		this.permanentAaddress = permanentAaddress;
	}

	@Column(name="permanentpincode", nullable=false)
	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
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

	@Column(name="officephoneno", nullable=true)
	public String getOfficePhoneNo() {
		return officePhoneNo;
	}

	public void setOfficePhoneNo(String officePhoneNo) {
		this.officePhoneNo = officePhoneNo;
	}

	@Column(name="officeemailid", nullable=true)
	public String getOfficeEmailId() {
		return officeEmailId;
	}

	public void setOfficeEmailId(String officeEmailId) {
		this.officeEmailId = officeEmailId;
	}

	/*@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	@Column(name="isactive", nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name="dob", nullable=false)
	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	@Column(name="joingdate", nullable=false)
	public Calendar getJoingDate() {
		return joingDate;
	}

	public void setJoingDate(Calendar joingDate) {
		this.joingDate = joingDate;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "city_id", nullable = false)
	public CityTO getCity() {
		return city;
	}

	public void setCity(CityTO city) {
		this.city = city;
	}

	/*@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "state_id", nullable = false)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}*/

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name = "designation_id", nullable = false)
	public DesignationTO getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationTO designation) {
		this.designation = designation;
	}
	
}
