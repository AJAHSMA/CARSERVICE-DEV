package com.ajahsma.carservice.dao;

import java.io.Serializable;
import java.util.List;

import com.ajahsma.carservice.model.Domain;

/**
 * @author SHARAN A
 */

public interface DefaultDao {

	Long saveDomain(Domain domain);
	
	Domain loadDomain(Class<? extends Domain> domainClass, Serializable id);
	
	Domain getDomain(Class<? extends Domain> domainClass, Serializable id);

	public List<Domain> getAllDomain(Class<? extends Domain> domainClass);
	
	void deleteDomain(Domain domain);
	
	void updateDomain(Domain domain);
	
	public Domain get(Class<? extends Domain> domainClass, Serializable id);

}
