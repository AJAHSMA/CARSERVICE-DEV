package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.DesignationDao;
import com.ajahsma.carservice.service.DesignationManager;

/**
 * @author SHARAN A
 */

@Service("myDesignationManager")
@Transactional
public class DesignationManagerImpl extends DefaultManagerImpl implements DesignationManager {


	@Autowired
	public void setDefaultDao(DesignationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private DesignationDao getDesignationDao() {
		return (DesignationDao) getDefaultDao();
	}

	
}
