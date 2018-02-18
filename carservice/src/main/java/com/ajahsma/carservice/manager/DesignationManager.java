package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.DesignationDTO;
import com.ajahsma.carservice.model.DesignationTO;

/**
 * @author SHARAN A
 */

public interface DesignationManager extends DefaultManager {

	public DesignationTO convertDesignationDTOToDesignationTO(DesignationDTO designationDTO) throws InstantiationException, IllegalAccessException;

}
