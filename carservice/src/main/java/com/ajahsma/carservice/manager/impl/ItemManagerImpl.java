package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ItemDao;
import com.ajahsma.carservice.manager.ItemManager;

/**
 * @author SHARAN A
 */

@Service
public class ItemManagerImpl extends DefaultManagerImpl implements ItemManager {


	@Autowired
	public void setDefaultDao(ItemDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ItemDao getItemDao() {
		return (ItemDao) getDefaultDao();
	}

	
}
