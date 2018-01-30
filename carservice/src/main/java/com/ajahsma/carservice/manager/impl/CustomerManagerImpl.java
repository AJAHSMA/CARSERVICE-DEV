package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CustomerDao;
import com.ajahsma.carservice.manager.CustomerManager;

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

	
}
