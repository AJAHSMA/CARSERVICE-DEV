package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.CountryDTO;
import com.ajahsma.carservice.model.CountryTO;

/**
 * @author SHARAN A
 */

public interface CountryManager extends DefaultManager {

	public CountryTO convertCountryDTOToCountryTO(CountryDTO countryDTO) throws InstantiationException, IllegalAccessException;
	
}
