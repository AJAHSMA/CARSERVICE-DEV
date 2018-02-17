package com.ajahsma.carservice.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
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

		Criteria criteria = createCriteria(applicationUserTO.getClass());
		criteria.add(Restrictions.eq("userName", applicationUserTO.getUserName()));

		return criteria.list();

	}

	@Override
	public ApplicationUserTO findByUserName(String userName) {
		Query query = null;

		String hqlQuery = "SELECT applicationuser FROM ApplicationUser applicationuser where applicationuser.userName=:userName";

		query = createQuery(hqlQuery);

		query.setParameter("userName", userName);

		return (ApplicationUserTO) query.uniqueResult();

	}

}
