package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.CarMakeDTO;
import com.ajahsma.carservice.model.CarMakeTO;

/**
 * @author SHARAN A
 */

public interface CarMakeManager extends DefaultManager {

	public CarMakeTO convertCarMakeDTOToCarMakeTO(CarMakeDTO carMakeDTO) throws InstantiationException, IllegalAccessException;
	
}
