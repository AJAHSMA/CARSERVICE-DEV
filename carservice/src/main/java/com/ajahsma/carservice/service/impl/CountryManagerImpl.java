package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CountryDao;
import com.ajahsma.carservice.service.CountryManager;

/**
 * @author SHARAN A
 */

@Service("myCountryManager")
@Transactional
public class CountryManagerImpl extends DefaultManagerImpl implements CountryManager {


	@Autowired
	public void setDefaultDao(CountryDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CountryDao getCountryDao() {
		return (CountryDao) getDefaultDao();
	}

	
}
