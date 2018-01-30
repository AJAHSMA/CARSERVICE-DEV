package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CountryDao;
import com.ajahsma.carservice.manager.CountryManager;

/**
 * @author SHARAN A
 */

@Service
public class CountryManagerImpl extends DefaultManagerImpl implements CountryManager {


	@Autowired
	public void setDefaultDao(CountryDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CountryDao getCountryDao() {
		return (CountryDao) getDefaultDao();
	}

	
}
