package com.ajahsma.carservice.controller;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;

import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.Domain;

/**
 * @author SHARAN A
 */
public abstract class AbstractController {

//	private DefaultManager defaultManager;
	private Domain domain;
	private Boolean isLoadLazy;
	

	abstract protected DefaultManager getDefaultManager();
	
	protected void saveDomain(Domain domain) {
		beforeProcess(domain);
		if(BooleanUtils.isTrue(validateDomain())) {
			getDefaultManager().saveDomain(domain);
		}
		
	}
	
	protected void updateDomain(Domain domain) {
		beforeProcess(domain);
		if(BooleanUtils.isTrue(validateDomain())) {
			getDefaultManager().updateDomain(domain);;
		}
	}
	
	protected void deleteDomain(Class<? extends Domain> domainClass, Long id) {
		
		Domain domain = getDefaultManager().getDomain(domainClass, id);

		beforeProcess(domain);
		if(BooleanUtils.isTrue(validateDomain())) {
			getDefaultManager().deleteDomain(domain);;
		}
	}
	
	protected void saveAllDomains(List<? extends Domain> domains) {
		if(!CollectionUtils.isEmpty(domains)) {
			if (!CollectionUtils.isEmpty(domains)) {
				for (Domain domain : domains) {
					getDefaultManager().saveDomain(domain);
				}
			}
		}
	}
	
	protected List<? extends Domain> getAllDomains(Class<? extends Domain> domainClass) {
		
		List<Domain> domains = null;
		if(domainClass != null) {
			domains = getDefaultManager().getAllDomain(domainClass);
		}
		return domains;		
	}
	
	protected List<? extends Domain> getAllDomains(Class<? extends Domain> domainClass, String nestedPathToInitialize) {
		
		String[] nestedPathsToInitialize = convertStringArray(nestedPathToInitialize);

		return getAllDomains(domainClass, nestedPathsToInitialize);		
	}
	
	protected List<? extends Domain> getAllDomains(Class<? extends Domain> domainClass, String[] nestedPathsToInitialize) {
		
		List<Domain> domains = null;
		if(domainClass != null) {
			domains = getDefaultManager().getAllDomain(domainClass, nestedPathsToInitialize);
		}
		return domains;		
	}
	
	protected String[] getNestedPathsToInitializeForServiceFind() {
		// do  nothing
		return null;
	}


	
	protected Boolean validateDomain() {
		return true;
	}

	protected void beforeProcess(Domain domain) {
		setDomain(domain);
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	protected Boolean getIsLoadLazy() {
		return isLoadLazy;
	}

	protected void setIsLoadLazy(Boolean isLoadLazy) {
		this.isLoadLazy = isLoadLazy;
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
