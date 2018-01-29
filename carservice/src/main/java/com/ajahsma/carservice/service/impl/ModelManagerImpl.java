package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ModelDao;
import com.ajahsma.carservice.service.ModelManager;

/**
 * @author SHARAN A
 */

@Service("myModelManager")
@Transactional
public class ModelManagerImpl extends DefaultManagerImpl implements ModelManager {


	@Autowired
	public void setDefaultDao(ModelDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ModelDao getModelDao() {
		return (ModelDao) getDefaultDao();
	}

	
}
