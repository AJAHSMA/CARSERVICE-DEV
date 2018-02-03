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
	
	Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize);
	
	Domain loadDomain(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize);
	
	Domain getDomain(Class<? extends Domain> domainClass, Serializable id);

	Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize);

	Domain getDomain(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize);

	public List<Domain> getAllDomain(Class<? extends Domain> domainClass);
	
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String nestedPathToInitialize);
	
	public List<Domain> getAllDomain(Class<? extends Domain> domainClass, String[] nestedPathsToInitialize);
	
	public Domain get(Class<? extends Domain> domainClass, Serializable id);

	public Domain get(Class<? extends Domain> domainClass, Serializable id, String nestedPathToInitialize);

	public Domain get(Class<? extends Domain> domainClass, Serializable id, String[] nestedPathsToInitialize);

	void deleteDomain(Domain domain);
	
	void updateDomain(Domain domain);
	

}
