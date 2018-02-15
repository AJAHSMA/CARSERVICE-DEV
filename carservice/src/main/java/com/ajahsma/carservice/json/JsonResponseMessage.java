package com.ajahsma.carservice.json;

public class JsonResponseMessage {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String DATA = "date";
	
	public static final String FAILURE = "Failure";
	public static final String SUCCESS = "Success";
	public static final String EXCEPTION = "Exception";

	// ----------------- INFO -----------------------------------
	public static final String INFO_MESSAGE_CREATED_SUCCESSFULLY = "{0} created successfully";
	public static final String INFO_MESSAGE_EMPLOYEE_CREATED_SUCCESSFULLY = "Employee created successfully";
	public static final String INFO_MESSAGE_LOGGED_IN_SUCCESSFULLY = "logged in successfully";
	
	// ----------------- ERROR -----------------------------------
	public static final String EXCEPTION_MESSAGE = "Exception: {0}";
	
	// ----------------- EXCEPTION -----------------------------------
	public static final String ERROR_MESSAGE_ACCOUNT_LOCKED = "Account locked";
	public static final String ERROR_MESSAGE_INVALID_CREDENTIALS = "Invalid credentials";
}
