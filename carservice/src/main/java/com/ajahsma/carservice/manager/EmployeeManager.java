package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.EmployeeDTO;
import com.ajahsma.carservice.json.JsonResponse;

/**
 * @author SHARAN A
 */

public interface EmployeeManager extends DefaultManager {

	JsonResponse save(EmployeeDTO employeeDTO, String urlType);

}
