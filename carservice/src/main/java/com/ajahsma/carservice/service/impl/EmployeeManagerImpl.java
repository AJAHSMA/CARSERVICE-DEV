package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.EmployeeDao;
import com.ajahsma.carservice.service.EmployeeManager;

/**
 * @author SHARAN A
 */

@Service("myEmployeeManager")
@Transactional
public class EmployeeManagerImpl extends DefaultManagerImpl implements EmployeeManager {


	@Autowired
	public void setDefaultDao(EmployeeDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private EmployeeDao getEmployeeDao() {
		return (EmployeeDao) getDefaultDao();
	}

	
}
