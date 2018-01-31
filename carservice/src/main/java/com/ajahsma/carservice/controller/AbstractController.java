package com.ajahsma.carservice.controller;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;

import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.DesignationTO;
import com.ajahsma.carservice.model.Domain;

import java.util.List;

/**
 * @author SHARAN A
 */
public abstract class AbstractController {

//	private DefaultManager defaultManager;
	private Domain domain;
	

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

}
