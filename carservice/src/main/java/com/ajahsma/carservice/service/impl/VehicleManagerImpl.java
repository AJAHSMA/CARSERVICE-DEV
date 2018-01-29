package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.VehicleDao;
import com.ajahsma.carservice.service.VehicleManager;

/**
 * @author SHARAN A
 */

@Service("myVehicleManager")
@Transactional
public class VehicleManagerImpl extends DefaultManagerImpl implements VehicleManager {


	@Autowired
	public void setDefaultDao(VehicleDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private VehicleDao getVehicleDao() {
		return (VehicleDao) getDefaultDao();
	}

	
}
