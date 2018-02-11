package com.ajahsma.carservice.dto;

import java.util.Calendar;

/**
 * @author DEVU I
 */

public class ApplicationUserDTO extends AbstractIdDomain {

	private String userName;
	private String password;
	private Integer loginAttempts;
	private Calendar createDate;
	private Boolean isActive;
	private EmployeeDTO createBy;
	private EmployeeDTO employee;

	public ApplicationUserDTO() {

	}

	public ApplicationUserDTO(String userName, String password, Integer loginAttempts, Calendar createDate,
			Boolean isActive, EmployeeDTO createBy, EmployeeDTO employee) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginAttempts = loginAttempts;
		this.createDate = createDate;
		this.isActive = isActive;
		this.createBy = createBy;
		this.employee = employee;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public EmployeeDTO getCreateBy() {
		return createBy;
	}

	public void setCreateBy(EmployeeDTO createBy) {
		this.createBy = createBy;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

}
