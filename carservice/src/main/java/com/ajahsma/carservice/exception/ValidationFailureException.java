package com.ajahsma.carservice.exception;

/**
 * @author DEVU I
 */

public class ValidationFailureException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;

	public ValidationFailureException() {
		super();
	}

	public ValidationFailureException(String errorCode, String errorMsg) {
		super(errorMsg);
		setErrorCode(errorCode);
		setErrorMsg(errorMsg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
