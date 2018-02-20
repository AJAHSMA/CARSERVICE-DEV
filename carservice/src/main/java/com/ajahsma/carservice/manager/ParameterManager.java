package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.ParameterDTO;
import com.ajahsma.carservice.model.ParameterTO;

/**
 * @author SHARAN A
 */

public interface ParameterManager extends DefaultManager {

	public ParameterTO convertParameterDTOToParameterTO(ParameterDTO parameterDTO) throws InstantiationException, IllegalAccessException;
	
}
