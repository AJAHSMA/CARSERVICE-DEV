package com.ajahsma.carservice.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ajahsma.carservice.dao.DefaultDao;
import com.ajahsma.carservice.model.Domain;

/**
 * @author SHARAN A
 */

public class DefaultDaoImpl extends AbstractDefaultDaoImpl implements DefaultDao {
	
	/*@Autowired
	private SessionFactory sessionFactory;

	private Session getHibernateTemplate() {
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateExcepstion e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}*/
	
	protected Query createQuery(String queryString) {
		return getSession().createQuery(queryString);
	}
	
	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id) {
		Criteria criteria = createCriteria(domainClass);
		criteria.add(Restrictions.eq("id", id));
		return (Domain) criteria.list();
	}

	protected Criteria createCriteria(Class<?> persistentClass) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		return criteria;
	}

	@Override
	public Long saveDomain(Domain domain) {
		return (Long) getHibernateTemplate().save(domain);
	}

	@Override
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id) {
		
		return (Domain) getHibernateTemplate().load(domainClass, id);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id) {
		return (Domain) getHibernateTemplate().get(domainClass, id);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass) {
		Criteria criteria = createCriteria(domainClass);
		return (List<Domain>) criteria.list();
	}

	@Override
	public void deleteDomain(Domain domain) {
		getHibernateTemplate().delete(domain);
		getHibernateTemplate().flush();
	}

	@Override
	public void updateDomain(Domain domain) {
		getHibernateTemplate().update(domain);
		
	}
	
	private Session getSession() {
		try {
			return getHibernateTemplate().getSessionFactory().getCurrentSession();
		} catch (Exception e) {
			return getHibernateTemplate().getSessionFactory().openSession();
		}
	}


}
