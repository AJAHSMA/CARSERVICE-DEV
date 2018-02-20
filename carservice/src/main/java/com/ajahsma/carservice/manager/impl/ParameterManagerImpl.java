package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ParameterDao;
import com.ajahsma.carservice.dto.ParameterDTO;
import com.ajahsma.carservice.manager.ParameterManager;
import com.ajahsma.carservice.model.ParameterTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class ParameterManagerImpl extends DefaultManagerImpl implements ParameterManager {


	@Autowired
	public void setDefaultDao(ParameterDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ParameterDao getParameterDao() {
		return (ParameterDao) getDefaultDao();
	}

	@Override
	public ParameterTO convertParameterDTOToParameterTO(ParameterDTO parameterDTO) throws InstantiationException, IllegalAccessException {
		ParameterTO parameterTO = CarServiceUtils.copyBeanProperties(parameterDTO, ParameterTO.class);

		return parameterTO;
	}
	
}
