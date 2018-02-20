package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CarModelDao;
import com.ajahsma.carservice.dto.CarModelDTO;
import com.ajahsma.carservice.manager.CarMakeManager;
import com.ajahsma.carservice.manager.CarModelManager;
import com.ajahsma.carservice.model.CarMakeTO;
import com.ajahsma.carservice.model.CarModelTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class CarModelManagerImpl extends DefaultManagerImpl implements CarModelManager {

	private CarMakeManager carMakeManager;
	
	@Autowired
	public void setDefaultDao(CarModelDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CarModelDao getCarModelDao() {
		return (CarModelDao) getDefaultDao();
	}

	@Override
	public CarModelTO convertCarModelDTOToCarModelTO(CarModelDTO carModelDTO) throws InstantiationException, IllegalAccessException {
		CarModelTO carModelTO = CarServiceUtils.copyBeanProperties(carModelDTO, CarModelTO.class);
		CarMakeTO carMakeTO = null;
		
		if (carModelDTO.getCarMake() != null && carModelDTO.getCarMake().getId() != null) {
			carMakeTO = (CarMakeTO) carMakeManager.getDomain(CarMakeTO.class, carModelDTO.getCarMake().getId());

		}
		carModelTO.setCarMake(carMakeTO);

		return carModelTO;
	}
	
}
