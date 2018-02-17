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

@Entity(name = "ApplicationUser")
@Table(name = "applicationuser")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationUserTO extends AbstractIdDomain {

	private String userName;
	private String password;
	private Integer loginAttempts;
	private Calendar createDate;
	private Boolean isActive;
	private EmployeeTO createBy;
	private EmployeeTO employee;

	public ApplicationUserTO() {

	}

	public ApplicationUserTO(String userName, String password, Integer loginAttempts, Calendar createDate,
			Boolean isActive, EmployeeTO createBy, EmployeeTO employee) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginAttempts = loginAttempts;
		this.createDate = createDate;
		this.isActive = isActive;
		this.createBy = createBy;
		this.employee = employee;
	}

	@Column(name = "username", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "loginattempts", nullable = false)
	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	@Column(name = "createdate", nullable = false)
	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	@Column(name = "isactive", nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "createby_id", nullable = false)
	public EmployeeTO getCreateBy() {
		return createBy;
	}

	public void setCreateBy(EmployeeTO createBy) {
		this.createBy = createBy;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "employee_id", nullable = false)
	public EmployeeTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeTO employee) {
		this.employee = employee;
	}

}
