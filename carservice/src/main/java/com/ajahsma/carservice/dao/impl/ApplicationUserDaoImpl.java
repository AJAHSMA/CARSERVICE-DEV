package com.ajahsma.carservice.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ajahsma.carservice.dao.ApplicationUserDao;
import com.ajahsma.carservice.model.ApplicationUserTO;

/**
 * @author SHARAN A
 */

@Repository
@SuppressWarnings("unchecked")
public class ApplicationUserDaoImpl extends DefaultDaoImpl implements ApplicationUserDao {

	@Override
	public List<ApplicationUserTO> login(ApplicationUserTO applicationUserTO) {

		List<ApplicationUserTO> list = getSession().createCriteria(ApplicationUserTO.class, "applicationuser")
				.add(Restrictions.eq("applicationuser.userName", applicationUserTO.getUserName())).list();
		return list;
	}

}
