package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.NomenclatureDao;
import com.ajahsma.carservice.manager.NomenclatureManager;

/**
 * @author SHARAN A
 */

@Service
public class NomenclatureManagerImpl extends DefaultManagerImpl implements NomenclatureManager {


	@Autowired
	public void setDefaultDao(NomenclatureDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private NomenclatureDao getNomenclatureDao() {
		return (NomenclatureDao) getDefaultDao();
	}

	
}
