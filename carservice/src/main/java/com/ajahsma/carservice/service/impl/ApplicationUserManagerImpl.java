package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ApplicationUserDao;
import com.ajahsma.carservice.service.ApplicationUserManager;

/**
 * @author SHARAN A
 */

@Service("myApplicationUserManager")
@Transactional
public class ApplicationUserManagerImpl extends DefaultManagerImpl implements ApplicationUserManager {


	@Autowired
	public void setDefaultDao(ApplicationUserDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ApplicationUserDao getApplicationUserDao() {
		return (ApplicationUserDao) getDefaultDao();
	}

	
}
