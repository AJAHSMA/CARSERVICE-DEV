package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.StateDao;
import com.ajahsma.carservice.service.StateManager;

/**
 * @author SHARAN A
 */

@Service("myStateManager")
@Transactional
public class StateManagerImpl extends DefaultManagerImpl implements StateManager {


	@Autowired
	public void setDefaultDao(StateDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private StateDao getStateDao() {
		return (StateDao) getDefaultDao();
	}

	
}
