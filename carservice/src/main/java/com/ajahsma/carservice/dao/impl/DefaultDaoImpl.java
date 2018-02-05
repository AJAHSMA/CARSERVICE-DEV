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

	protected Query createQuery(String queryString) {
		return getSession().createQuery(queryString);
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id) {
		Criteria criteria = createCriteria(domainClass);
		criteria.add(Restrictions.eq("id", id));
		return (Domain) criteria.list();
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize) {
		String[] nestedPathsToInitialize = convertStringArray(nestedPathToInitialize);
		return get(domainClass, id, nestedPathsToInitialize);
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize) {

		return initializeNestedPathsOfDomain(get(domainClass, id), nestedPathsToInitialize);
		
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
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize) {
		String[] nestedPathsToInitialize = convertStringArray(nestedPathToInitialize);
		return loadDomain(domainClass, id, nestedPathsToInitialize);
	}

	@Override
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize) {
		return initializeNestedPathsOfDomain(loadDomain(domainClass, id), nestedPathsToInitialize);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id) {
		return (Domain) getHibernateTemplate().get(domainClass, id);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize) {
		String[] nestedPathsToInitialize = convertStringArray(nestedPathToInitialize);
		
		return getDomain(domainClass, id, nestedPathsToInitialize);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize) {
		return initializeNestedPathsOfDomain(getDomain(domainClass, id), nestedPathsToInitialize);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass) {
		Criteria criteria = createCriteria(domainClass);
		return (List<Domain>) criteria.list();
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String nestedPathToInitialize) {
		String[] nestedPathsToInitialize = convertStringArray(nestedPathToInitialize);
		return getAllDomain(domainClass, nestedPathsToInitialize);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String[] nestedPathsToInitialize) {
		return initializeNestedPathsOfListResults(getAllDomain(domainClass), nestedPathsToInitialize);
	}

	@Override
	public void deleteDomain(Domain domain) {
		getHibernateTemplate().delete(domain);
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

	private String[] convertStringArray(String value) {
		String[] nestedPathsToInitialize = null;
		if (value != null) {
			nestedPathsToInitialize = new String[1];
			nestedPathsToInitialize[0] = value;
		}
		return nestedPathsToInitialize;
	}
}
