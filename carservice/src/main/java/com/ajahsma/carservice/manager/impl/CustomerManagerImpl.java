package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CustomerDao;
import com.ajahsma.carservice.dto.CustomerDTO;
import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.model.CustomerTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class CustomerManagerImpl extends DefaultManagerImpl implements CustomerManager {


	@Autowired
	public void setDefaultDao(CustomerDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CustomerDao getCustomerDao() {
		return (CustomerDao) getDefaultDao();
	}

	@Override
	public CustomerTO convertCustomerDTOToCustomerTO(CustomerDTO customerDTO) throws InstantiationException, IllegalAccessException {
		CustomerTO customerTO = CarServiceUtils.copyBeanProperties(customerDTO, CustomerTO.class);

		return customerTO;
	}
	
}
