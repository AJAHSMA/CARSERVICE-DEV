package com.ajahsma.carservice.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ApplicationUserDao;
import com.ajahsma.carservice.dao.EmployeeDao;
import com.ajahsma.carservice.dto.EmployeeDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.BusinessException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.EmployeeManager;
import com.ajahsma.carservice.model.ApplicationUserTO;
import com.ajahsma.carservice.model.CityTO;
import com.ajahsma.carservice.model.DesignationTO;
import com.ajahsma.carservice.model.EmployeeTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.DESEncryptionUtil;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Service
public class EmployeeManagerImpl extends DefaultManagerImpl implements EmployeeManager {


	private static Logger logger = Logger.getLogger(EmployeeManagerImpl.class);

	private final String CLASS_NAME = EmployeeManagerImpl.class.getSimpleName();
	
	@Autowired
	private ApplicationUserDao applicationUserDao;
	
	@Autowired
	public void setDefaultDao(EmployeeDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private EmployeeDao getEmployeeDao() {
		return (EmployeeDao) getDefaultDao();
	}
	
	@Override
	public JsonResponse save(EmployeeDTO employeeDTO, String urlType) 
	{
		logger.info("Entering :: " + CLASS_NAME + " :: save method");
		Map<String, Object> items = new HashMap<>();
		try {
			Object userName = applicationUserDao.findByUserName(employeeDTO.getName()+employeeDTO.getGardianName());
			if(!CarServiceUtils.isNull(userName))
			{
				throw new BusinessException(ErrorCodes.UAE.name(), ErrorCodes.UAE.value());
			}
			EmployeeTO employeeTO = CarServiceUtils.copyBeanProperties(employeeDTO, EmployeeTO.class);
			CityTO cityTO = CarServiceUtils.copyBeanProperties(employeeDTO.getCity(), CityTO.class);
			DesignationTO designationTO = CarServiceUtils.copyBeanProperties(employeeDTO.getDesignation(), DesignationTO.class);
			employeeTO.setCity(cityTO);
			employeeTO.setDesignation(designationTO);
			employeeTO.setJoingDate(Calendar.getInstance());
			saveDomain(employeeTO); //Save Employee Details
			ApplicationUserTO applicationUserTO = new ApplicationUserTO();
			applicationUserTO.setUserName(employeeTO.getName()+employeeTO.getGardianName());
			applicationUserTO.setPassword(DESEncryptionUtil.encrypt(employeeTO.getDob().toString()));
			applicationUserTO.setEmployee(employeeTO);
			applicationUserTO.setCreateBy(employeeTO);
			applicationUserTO.setCreateDate(Calendar.getInstance());
			applicationUserTO.setIsActive(true);
			applicationUserTO.setLoginAttempts(0);
			saveDomain(applicationUserTO);

			items.put(JsonResponse.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponse.MESSAGE, JsonResponseMessage.INFO_EMPLOYEE_CREATED_SUCCESSFULLY);

			return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
			
		} catch (Exception e) {
			
			logger.info("Error :: " + CLASS_NAME + " :: save method", e);
			items.put(JsonResponse.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponse.MESSAGE, JsonResponseMessage.EXCEPTION);
			return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
		}
	}

	
	
}
