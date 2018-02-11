package com.ajahsma.carservice.dto;

import java.io.Serializable;

/**
 * @author DEVU I
 */
public interface Domain extends Serializable {

	boolean equals(Object other);

	int hashCode();

}
