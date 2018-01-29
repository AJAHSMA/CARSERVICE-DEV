package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ItemDao;
import com.ajahsma.carservice.service.ItemManager;

/**
 * @author SHARAN A
 */

@Service("myItemManager")
@Transactional
public class ItemManagerImpl extends DefaultManagerImpl implements ItemManager {


	@Autowired
	public void setDefaultDao(ItemDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ItemDao getItemDao() {
		return (ItemDao) getDefaultDao();
	}

	
}
