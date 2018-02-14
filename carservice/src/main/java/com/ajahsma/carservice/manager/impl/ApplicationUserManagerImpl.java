package com.ajahsma.carservice.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ApplicationUserDao;
import com.ajahsma.carservice.dto.ApplicationUserDTO;
import com.ajahsma.carservice.dto.CityDTO;
import com.ajahsma.carservice.dto.CountryDTO;
import com.ajahsma.carservice.dto.DesignationDTO;
import com.ajahsma.carservice.dto.EmployeeDTO;
import com.ajahsma.carservice.dto.StateDTO;
import com.ajahsma.carservice.json.Data;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.ApplicationUserManager;
import com.ajahsma.carservice.model.ApplicationUserTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.DESEncryptionUtil;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Service
public class ApplicationUserManagerImpl extends DefaultManagerImpl implements ApplicationUserManager {

	private static Logger logger = Logger.getLogger(ApplicationUserManagerImpl.class);

	private final String CLASS_NAME = ApplicationUserManagerImpl.class.getSimpleName();
	@Autowired
	private ApplicationUserDao applicationUserDao;

	@Override
	public JsonResponse login(ApplicationUserDTO applicationUserDTO, String urlType) {
		logger.info("Entering :: " + CLASS_NAME + " :: login method");
		Map<String, Object> items = new HashMap<>();
		try {
			ApplicationUserTO applicationUserTO = CarServiceUtils.copyBeanProperties(applicationUserDTO,
					ApplicationUserTO.class);
			List<ApplicationUserTO> applicationUser = applicationUserDao.login(applicationUserTO);
			if (CarServiceUtils.isListNotNullAndEmpty(applicationUser)) {
				ApplicationUserTO appUserTO = applicationUser.get(0);
				if (appUserTO.getLoginAttempts() == 5 || appUserTO.getIsActive() == false) {

					items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
					items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.ERROR_ACCOUNT_LOCKED);

					return JSONHelperUtil.getJsonResponse("1.0", "/employee/login", items);
				}
				if ((applicationUserTO.getPassword()).equals(appUserTO.getPassword())) {
					appUserTO.setLoginAttempts(0);
					applicationUserDao.updateDomain(appUserTO);
					EmployeeDTO employeeDTO = CarServiceUtils.copyBeanProperties(appUserTO.getEmployee(), EmployeeDTO.class);
					DesignationDTO designationDTO = CarServiceUtils.copyBeanProperties(appUserTO.getEmployee().getDesignation(), DesignationDTO.class);
					CountryDTO countryDTO = CarServiceUtils.copyBeanProperties(appUserTO.getEmployee().getCity().getState().getCountry(), CountryDTO.class);
					StateDTO stateDTO = CarServiceUtils.copyBeanProperties(appUserTO.getEmployee().getCity().getState(), StateDTO.class);
					CityDTO cityDTO = CarServiceUtils.copyBeanProperties(appUserTO.getEmployee().getCity(), CityDTO.class);
					employeeDTO.setDesignation(designationDTO);
					stateDTO.setCountry(countryDTO);
					cityDTO.setState(stateDTO);
					employeeDTO.setCity(cityDTO);
					
					items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
					items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.INFO_LOGGED_IN_SUCCESSFULLY);
					items.put(JsonResponseMessage.DATA, employeeDTO);
					
					return JSONHelperUtil.getJsonResponse("1.0", "/employee/login", items);
				} else {
					appUserTO.setLoginAttempts(appUserTO.getLoginAttempts() + 1);
					applicationUserDao.updateDomain(appUserTO);
					items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
					items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.ERROR_INVALID_CREDENTIALS);
					return JSONHelperUtil.getJsonResponse("1.0", "/employee/login", items);
				}
			} else {
				items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
				items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.ERROR_INVALID_CREDENTIALS);
				return JSONHelperUtil.getJsonResponse("1.0", "/employee/login", items);
			}
		} catch (Exception e) {
			logger.info("Error :: " + CLASS_NAME + " :: login method", e);
			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.EXCEPTION);
			return JSONHelperUtil.getJsonResponse("1.0", "/employee/login", items);
		}
	}

}
