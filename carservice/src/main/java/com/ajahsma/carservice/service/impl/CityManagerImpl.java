package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CityDao;
import com.ajahsma.carservice.service.CityManager;

/**
 * @author SHARAN A
 */

@Service("myCityManager")
@Transactional
public class CityManagerImpl extends DefaultManagerImpl implements CityManager {


	@Autowired
	public void setDefaultDao(CityDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CityDao getCityDao() {
		return (CityDao) getDefaultDao();
	}

	
}
