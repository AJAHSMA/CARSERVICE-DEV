package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CityDao;
import com.ajahsma.carservice.dto.CityDTO;
import com.ajahsma.carservice.manager.CityManager;
import com.ajahsma.carservice.manager.StateManager;
import com.ajahsma.carservice.model.CityTO;
import com.ajahsma.carservice.model.StateTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class CityManagerImpl extends DefaultManagerImpl implements CityManager {

	@Autowired
	private StateManager stateManager;

	@Autowired
	public void setDefaultDao(CityDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CityDao getCityDao() {
		return (CityDao) getDefaultDao();
	}

	@Override
	public CityTO convertCityDTOToCityTO(CityDTO cityDTO) throws InstantiationException, IllegalAccessException {
		CityTO cityTO = CarServiceUtils.copyBeanProperties(cityDTO, CityTO.class);
		StateTO stateTO = null;
		
		if (cityDTO.getState() != null && cityDTO.getState().getId() != null) {
			stateTO = (StateTO) stateManager.getDomain(StateTO.class, cityDTO.getState().getId());

		}
		cityTO.setState(stateTO);

		return cityTO;
	}
	
}
