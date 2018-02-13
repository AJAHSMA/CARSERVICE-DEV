package com.ajahsma.carservice.dao.impl;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public Object findByUserName(String userName) 
	{
		//Hql query is not working	
		/*Query query = null;
		String hqlQuery = "SELECT applicationuser FROM ApplicationUserTO applicationuser where applicationuser.userName=:userName";
		query = getSession().createQuery(hqlQuery);
		query.setParameter("userName", userName);
		return query.uniqueResult();*/
	
		return getSession().createCriteria(ApplicationUserTO.class, "applicationuser").add(Restrictions.eq("applicationuser.userName", userName)).uniqueResult();
	}

}
