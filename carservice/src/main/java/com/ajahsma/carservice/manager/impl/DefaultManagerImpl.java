package com.ajahsma.carservice.manager.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.ajahsma.carservice.dao.DefaultDao;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.Domain;

/**
 * @author SHARAN A
 */

public class DefaultManagerImpl implements DefaultManager {

	protected final Log logger = LogFactory.getLog(getClass());

	protected DefaultDao defaultDao;
	private Domain domain;

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public DefaultDao getDefaultDao() {
		return this.defaultDao;
	}

	public Domain createNewTransientDomain(String domainClassName) throws Exception {
		return createNewTransientDomain(domainClassName, false);
	}

	public Domain createNewTransientDomain(String domainClassName, Boolean isSearchMode) throws Exception {

		Domain newDomainObject;
		try {
			newDomainObject = (Domain) BeanUtils.instantiateClass(Class.forName(domainClassName));
		} catch (Exception e) {
			throw new Exception(e);
		}

		return newDomainObject;
	}

	@Override
	@Transactional
	public Serializable saveDomain(Domain domain) {
		Serializable id = null;
		
		initSave(domain);
	    validateSave(domain);
	    id = getDefaultDao().saveDomain(domain);
	    afterSave(domain);

		return id;
	}

	@Override
	@Transactional
	public void saveAll(List<Domain> domains) {
		if (!CollectionUtils.isEmpty(domains)) {
			for (Domain domain : domains) {
				getDefaultDao().saveDomain(domain);
			}
		}

	}

	@Override
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id) {
		return getDefaultDao().loadDomain(domainClass, id);
	}

	@Override
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String nesteddPathToInitialize) {
		return getDefaultDao().loadDomain(domainClass, id, nesteddPathToInitialize);
	}

	@Override
	public Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String[] nesteddPathsToInitialize) {
		return getDefaultDao().loadDomain(domainClass, id, nesteddPathsToInitialize);
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id) {
		return getDefaultDao().get(domainClass, id);
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize) {
		return getDefaultDao().get(domainClass, id, nestedPathToInitialize);
	}

	@Override
	public Domain get(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize) {
		return getDefaultDao().get(domainClass, id, nestedPathsToInitialize);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id) {
		return getDefaultDao().getDomain(domainClass, id);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize) {
		return getDefaultDao().getDomain(domainClass, id, nestedPathToInitialize);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize) {
		return getDefaultDao().getDomain(domainClass, id, nestedPathsToInitialize);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass) {
		return getDefaultDao().getAllDomain(domainClass);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String nestedPathToInitialize) {
		return getDefaultDao().getAllDomain(domainClass, nestedPathToInitialize);
	}

	@Override
	@Transactional
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String[] nestedPathsToInitialize) {
		return getDefaultDao().getAllDomain(domainClass, nestedPathsToInitialize);
	}

	@Override
	@Transactional
	public void deleteDomain(Domain domain) {
		getDefaultDao().deleteDomain(domain);
	}

	@Override
	@Transactional
	public void updateDomain(Domain domain) {
		initUpdate(domain);
	    validateUpdate(domain);
	    getDefaultDao().updateDomain(domain);
	    afterUpdate(domain);
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	protected void initSave(Domain domain2) {
		// TODO Auto-generated method stub
	}

	protected void validateSave(Domain domain2) {
		// TODO Auto-generated method stub
	}

	protected void afterSave(Domain domain2) {
		// TODO Auto-generated method stub
	}

	protected void initUpdate(Domain domain2) {
		// TODO Auto-generated method stub
	}

	protected void validateUpdate(Domain domain2) {
		// TODO Auto-generated method stub
	}

	protected void afterUpdate(Domain domain2) {
		// TODO Auto-generated method stub
	}

}
