package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.CarModelDTO;
import com.ajahsma.carservice.model.CarModelTO;

/**
 * @author SHARAN A
 */

public interface CarModelManager extends DefaultManager {

	public CarModelTO convertCarModelDTOToCarModelTO(CarModelDTO carModelDTO) throws InstantiationException, IllegalAccessException;
}
