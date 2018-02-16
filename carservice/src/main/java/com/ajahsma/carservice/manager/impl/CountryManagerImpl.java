package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.CountryDao;
import com.ajahsma.carservice.dto.CountryDTO;
import com.ajahsma.carservice.manager.CountryManager;
import com.ajahsma.carservice.model.CountryTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class CountryManagerImpl extends DefaultManagerImpl implements CountryManager {


	@Autowired
	public void setDefaultDao(CountryDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private CountryDao getCountryDao() {
		return (CountryDao) getDefaultDao();
	}

	@Override
	public CountryTO convertCountryDTOToCountryTO(CountryDTO countryDTO) throws InstantiationException, IllegalAccessException {
		CountryTO countryTO = CarServiceUtils.copyBeanProperties(countryDTO, CountryTO.class);

		return countryTO;
	}
	
}
