package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.DesignationDao;
import com.ajahsma.carservice.manager.DesignationManager;

/**
 * @author SHARAN A
 */

@Service
public class DesignationManagerImpl extends DefaultManagerImpl implements DesignationManager {


	@Autowired
	public void setDefaultDao(DesignationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private DesignationDao getDesignationDao() {
		return (DesignationDao) getDefaultDao();
	}

	
}
