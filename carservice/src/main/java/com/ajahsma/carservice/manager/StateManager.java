package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.StateDTO;
import com.ajahsma.carservice.model.StateTO;

/**
 * @author SHARAN A
 */

public interface StateManager extends DefaultManager {

	public StateTO convertStateDTOToStateTO(StateDTO stateDTO) throws InstantiationException, IllegalAccessException;
}
