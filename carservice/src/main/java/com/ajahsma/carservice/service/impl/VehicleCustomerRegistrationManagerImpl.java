package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.VehicleCustomerRegistrationDao;
import com.ajahsma.carservice.service.VehicleCustomerRegistrationManager;

/**
 * @author SHARAN A
 */

@Service("myVehicleCustomerRegistrationManager")
@Transactional
public class VehicleCustomerRegistrationManagerImpl extends DefaultManagerImpl implements VehicleCustomerRegistrationManager {


	@Autowired
	public void setDefaultDao(VehicleCustomerRegistrationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private VehicleCustomerRegistrationDao getVehicleCustomerRegistrationDao() {
		return (VehicleCustomerRegistrationDao) getDefaultDao();
	}

	
}
