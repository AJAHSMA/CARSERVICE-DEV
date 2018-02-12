package com.ajahsma.carservice.enumeration;

/**
 * @author DEVU I
 */

public enum ErrorCodes 
{
	UAE("Username already exists"),
	EAE("Email already exists. please choose different one");
	
	private final String value;

	ErrorCodes(String value) {
        this.value = value;
    }
	
	public String value() {
        return value;
    }
	
}
