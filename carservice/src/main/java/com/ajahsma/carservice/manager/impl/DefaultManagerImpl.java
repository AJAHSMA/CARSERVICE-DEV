package com.ajahsma.carservice.manager.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

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
	public Long saveDomain(Domain domain) {
		return getDefaultDao().saveDomain(domain);
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
	public Domain get(Class<? extends Domain> domainClass, Serializable id) {
		return getDefaultDao().get(domainClass, id);
	}

	@Override
	public Domain getDomain(Class<? extends Domain> domainClass, Serializable id) {
		return getDefaultDao().getDomain(domainClass, id);
	}

	@Override
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass) {
		return getDefaultDao().getAllDomain(domainClass);
	}

	@Override
	public void deleteDomain(Domain domain) {
		getDefaultDao().deleteDomain(domain);
	}

	@Override
	@Transactional
	public void updateDomain(Domain domain) {
		getDefaultDao().updateDomain(domain);
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

}
