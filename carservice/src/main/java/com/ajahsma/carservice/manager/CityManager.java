package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.CityDTO;
import com.ajahsma.carservice.model.CityTO;

/**
 * @author SHARAN A
 */

public interface CityManager extends DefaultManager {

	public CityTO convertCityDTOToCityTO(CityDTO cityDTO) throws InstantiationException, IllegalAccessException;
	
}
