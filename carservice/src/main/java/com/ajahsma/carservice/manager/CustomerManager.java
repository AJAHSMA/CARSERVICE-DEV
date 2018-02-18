package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.CustomerDTO;
import com.ajahsma.carservice.model.CustomerTO;

/**
 * @author SHARAN A
 */

public interface CustomerManager extends DefaultManager {

	public CustomerTO convertCustomerDTOToCustomerTO(CustomerDTO customerDTO) throws InstantiationException, IllegalAccessException;

}
