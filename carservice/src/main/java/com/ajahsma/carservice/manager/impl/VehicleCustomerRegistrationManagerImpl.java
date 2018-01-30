package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.VehicleCustomerRegistrationDao;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;

/**
 * @author SHARAN A
 */

@Service
public class VehicleCustomerRegistrationManagerImpl extends DefaultManagerImpl implements VehicleCustomerRegistrationManager {


	@Autowired
	public void setDefaultDao(VehicleCustomerRegistrationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private VehicleCustomerRegistrationDao getVehicleCustomerRegistrationDao() {
		return (VehicleCustomerRegistrationDao) getDefaultDao();
	}

	
}
