package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.StateDao;
import com.ajahsma.carservice.dto.StateDTO;
import com.ajahsma.carservice.manager.CountryManager;
import com.ajahsma.carservice.manager.StateManager;
import com.ajahsma.carservice.model.CountryTO;
import com.ajahsma.carservice.model.StateTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class StateManagerImpl extends DefaultManagerImpl implements StateManager {

	@Autowired
	private CountryManager countryManager;

	@Autowired
	public void setDefaultDao(StateDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private StateDao getStateDao() {
		return (StateDao) getDefaultDao();
	}

	@Override
	public StateTO convertStateDTOToStateTO(StateDTO stateDTO) throws InstantiationException, IllegalAccessException {
		StateTO stateTO = CarServiceUtils.copyBeanProperties(stateDTO, StateTO.class);
		CountryTO countryTO = null;
		
		if (stateDTO.getCountry() != null && stateDTO.getCountry().getId() != null) {
			countryTO = (CountryTO) countryManager.getDomain(CountryTO.class, stateDTO.getCountry().getId());

		}
		stateTO.setCountry(countryTO);

		return stateTO;
	}
	
}
