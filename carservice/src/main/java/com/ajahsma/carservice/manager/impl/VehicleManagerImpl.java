package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.VehicleDao;
import com.ajahsma.carservice.dto.VehicleDTO;
import com.ajahsma.carservice.manager.CarModelManager;
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.CarModelTO;
import com.ajahsma.carservice.model.VehicleTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class VehicleManagerImpl extends DefaultManagerImpl implements VehicleManager {

	@Autowired
	private CarModelManager carModelManager;

	@Autowired
	public void setDefaultDao(VehicleDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private VehicleDao getVehicleDao() {
		return (VehicleDao) getDefaultDao();
	}

	@Override
	public VehicleTO convertVehicleDTOToVehocleTO(VehicleDTO vehicleDTO) throws InstantiationException, IllegalAccessException {
		VehicleTO vehicleTO = CarServiceUtils.copyBeanProperties(vehicleDTO, VehicleTO.class);
		CarModelTO carModelTO = null;
		;
		if (vehicleDTO.getCarModel() != null && vehicleDTO.getCarModel().getId() != null) {
			carModelTO = (CarModelTO) carModelManager.getDomain(CarModelTO.class, vehicleDTO.getCarModel().getId());

		}
		vehicleTO.setCarModel(carModelTO);

		return vehicleTO;
	}

	
}
