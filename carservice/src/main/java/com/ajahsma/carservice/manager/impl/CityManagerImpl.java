package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CityDao;
import com.ajahsma.carservice.manager.CityManager;

/**
 * @author SHARAN A
 */

@Service
public class CityManagerImpl extends DefaultManagerImpl implements CityManager {


	@Autowired
	public void setDefaultDao(CityDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CityDao getCityDao() {
		return (CityDao) getDefaultDao();
	}

	
}
