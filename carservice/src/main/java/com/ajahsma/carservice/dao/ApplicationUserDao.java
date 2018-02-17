package com.ajahsma.carservice.dao;

import java.util.List;

import com.ajahsma.carservice.model.ApplicationUserTO;

/**
 * @author SHARAN A
 */
public interface ApplicationUserDao extends DefaultDao {

	List<ApplicationUserTO> login(ApplicationUserTO applicationUserTO);

	ApplicationUserTO findByUserName(String string);

}
