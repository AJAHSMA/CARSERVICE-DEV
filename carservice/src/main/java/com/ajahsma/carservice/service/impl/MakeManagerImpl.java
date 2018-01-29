package com.ajahsma.carservice.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.MakeDao;
import com.ajahsma.carservice.service.MakeManager;

/**
 * @author SHARAN A
 */

@Service("myMakeManager")
@Transactional
public class MakeManagerImpl extends DefaultManagerImpl implements MakeManager {


	@Autowired
	public void setDefaultDao(MakeDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private MakeDao getMakeDao() {
		return (MakeDao) getDefaultDao();
	}

	
}
