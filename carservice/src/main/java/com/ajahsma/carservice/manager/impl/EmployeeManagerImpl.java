package com.ajahsma.carservice.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ajahsma.carservice.dao.ApplicationUserDao;
import com.ajahsma.carservice.dao.EmployeeDao;
import com.ajahsma.carservice.dto.EmployeeDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.BusinessException;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.ApplicationUserManager;
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
	private ApplicationUserManager applicationUserManager;

	@Autowired
	public void setDefaultDao(EmployeeDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private EmployeeDao getEmployeeDao() {
		return (EmployeeDao) getDefaultDao();
	}

	@Override
	@Transactional
	public JsonResponse save(EmployeeDTO employeeDTO, String urlType) {
		logger.info("Entering :: " + CLASS_NAME + " :: save method");
		Map<String, Object> items = new HashMap<>();
		try {
			validateDomain(employeeDTO);
			String name = CarServiceUtils.toCamelCase(employeeDTO.getName());
			String gardianName = CarServiceUtils.toCamelCase(employeeDTO.getGardianName());
			String userName = name.concat(gardianName);
			ApplicationUserTO applicatioUser = (ApplicationUserTO) applicationUserManager.findByUserName(userName);
			System.out.println("username " + applicatioUser);
			if (!CarServiceUtils.isNull(applicatioUser)) {
				throw new BusinessException(ErrorCodes.UAE.name(), ErrorCodes.UAE.value());
			}
			EmployeeTO employeeTO = CarServiceUtils.copyBeanProperties(employeeDTO, EmployeeTO.class);
			CityTO cityTO = CarServiceUtils.copyBeanProperties(employeeDTO.getCity(), CityTO.class);
			DesignationTO designationTO = CarServiceUtils.copyBeanProperties(employeeDTO.getDesignation(),
					DesignationTO.class);
			employeeTO.setCity(cityTO);
			employeeTO.setDesignation(designationTO);
			employeeTO.setJoingDate(Calendar.getInstance());
			saveDomain(employeeTO); // Save Employee Details
			ApplicationUserTO applicationUserTO = new ApplicationUserTO();
			applicationUserTO.setUserName(employeeTO.getName() + employeeTO.getGardianName());
			employeeTO.setDob(Calendar.getInstance());
			applicationUserTO.setPassword(DESEncryptionUtil.encrypt(employeeTO.getDob().toString()));
			applicationUserTO.setEmployee(employeeTO);
			applicationUserTO.setCreateBy(employeeTO);
			applicationUserTO.setCreateDate(Calendar.getInstance());
			applicationUserTO.setIsActive(true);
			applicationUserTO.setLoginAttempts(0);
			saveDomain(applicationUserTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE,
					CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Employee"));
		} catch (BusinessException exception) {
			logger.info("Error :: " + CLASS_NAME + " :: save method", exception);
			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, exception.getMessage());
		} catch (Exception e) {
			logger.info("Error :: " + CLASS_NAME + " :: save method", e);
			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE,
					CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));
		}
		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
	}

	private void validateDomain(EmployeeDTO employeeDTO) throws ValidationFailureException
	{
		boolean validateSuccess = true;
		StringBuilder stringBuilder = new StringBuilder("");
		if(StringUtils.isEmpty(employeeDTO.getName()))
		{
			validateSuccess = false;
			stringBuilder.append("Name is mandatory").append(", ");
		}
		else if(StringUtils.isEmpty(employeeDTO.getGardianName()))
		{
			validateSuccess = false;
			stringBuilder.append("Guardian is mandatory").append(", ");
		}
		else if(StringUtils.isEmpty(employeeDTO.getIdProofNo()))
		{
			validateSuccess = false;
			stringBuilder.append("Id proof is mandatory").append(", ");
		}
		else if(StringUtils.isEmpty(employeeDTO.getCity().getCode()))
		{
			validateSuccess = false;
			stringBuilder.append("City is mandatory").append(", ");
		}
		else if(StringUtils.isEmpty(employeeDTO.getPincode()))
		{
			validateSuccess = false;
			stringBuilder.append("Pincode is mandatory").append(", ");
		}
		
		if(!validateSuccess)
		{
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}
