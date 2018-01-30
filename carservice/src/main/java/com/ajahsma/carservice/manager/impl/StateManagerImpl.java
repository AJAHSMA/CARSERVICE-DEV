package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.StateDao;
import com.ajahsma.carservice.manager.StateManager;

/**
 * @author SHARAN A
 */

@Service
public class StateManagerImpl extends DefaultManagerImpl implements StateManager {


	@Autowired
	public void setDefaultDao(StateDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private StateDao getStateDao() {
		return (StateDao) getDefaultDao();
	}

	
}
