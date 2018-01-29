package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CustomerDao;
import com.ajahsma.carservice.service.CustomerManager;

/**
 * @author SHARAN A
 */

@Service("myCustomerManager")
@Transactional
public class CustomerManagerImpl extends DefaultManagerImpl implements CustomerManager {


	@Autowired
	public void setDefaultDao(CustomerDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CustomerDao getCustomerDao() {
		return (CustomerDao) getDefaultDao();
	}

	
}
