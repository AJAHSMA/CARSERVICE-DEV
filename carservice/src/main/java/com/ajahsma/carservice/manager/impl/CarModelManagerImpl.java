package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CarModelDao;
import com.ajahsma.carservice.manager.CarModelManager;

/**
 * @author SHARAN A
 */

@Service
public class CarModelManagerImpl extends DefaultManagerImpl implements CarModelManager {


	@Autowired
	public void setDefaultDao(CarModelDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CarModelDao getCarModelDao() {
		return (CarModelDao) getDefaultDao();
	}

	
}
