package com.ajahsma.carservice.enumeration;

/**
 * @author DEVU I
 */

public enum ErrorCodes 
{
	UAE("Username already exists"),
	VALIDATION_FAILURE("Validation Failed"),
	EAE("Email already exists. please choose different one");
	
	private final String value;

	ErrorCodes(String value) {
        this.value = value;
    }
	
	public String value() {
        return value;
    }
	
	public static ErrorCodes fromValue(String value) {
        for (ErrorCodes appCode : ErrorCodes.values()) {
            if (appCode.value.equals(value)) {
                return appCode;
            }
        }
        throw new IllegalArgumentException(value);
    }

	
}
