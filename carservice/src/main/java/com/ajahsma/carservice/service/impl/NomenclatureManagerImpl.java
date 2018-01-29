package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.NomenclatureDao;
import com.ajahsma.carservice.service.NomenclatureManager;

/**
 * @author SHARAN A
 */

@Service("myNomenclatureManager")
@Transactional
public class NomenclatureManagerImpl extends DefaultManagerImpl implements NomenclatureManager {


	@Autowired
	public void setDefaultDao(NomenclatureDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private NomenclatureDao getNomenclatureDao() {
		return (NomenclatureDao) getDefaultDao();
	}

	
}
