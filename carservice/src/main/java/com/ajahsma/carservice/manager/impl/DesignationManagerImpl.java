package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.DesignationDao;
import com.ajahsma.carservice.dto.DesignationDTO;
import com.ajahsma.carservice.manager.DesignationManager;
import com.ajahsma.carservice.model.CarModelTO;
import com.ajahsma.carservice.model.DesignationTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class DesignationManagerImpl extends DefaultManagerImpl implements DesignationManager {


	@Autowired
	public void setDefaultDao(DesignationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private DesignationDao getDesignationDao() {
		return (DesignationDao) getDefaultDao();
	}



	@Override
	public DesignationTO convertDesignationDTOToDesignationTO(DesignationDTO designationDTO) throws InstantiationException, IllegalAccessException {
		DesignationTO designationTO = CarServiceUtils.copyBeanProperties(designationDTO, DesignationTO.class);

		return designationTO;
	}	
}
