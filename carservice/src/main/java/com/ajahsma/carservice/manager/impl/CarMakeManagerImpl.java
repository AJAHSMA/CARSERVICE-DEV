package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CarMakeDao;
import com.ajahsma.carservice.dto.CarMakeDTO;
import com.ajahsma.carservice.manager.CarMakeManager;
import com.ajahsma.carservice.model.CarMakeTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class CarMakeManagerImpl extends DefaultManagerImpl implements CarMakeManager {


	@Autowired
	public void setDefaultDao(CarMakeDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CarMakeDao getCarMakeDao() {
		return (CarMakeDao) getDefaultDao();
	}

	@Override
	public CarMakeTO convertCarMakeDTOToCarMakeTO(CarMakeDTO carMakeDTO) throws InstantiationException, IllegalAccessException {
		CarMakeTO carMakeTO = CarServiceUtils.copyBeanProperties(carMakeDTO, CarMakeTO.class);

		return carMakeTO;
	}
	
}
