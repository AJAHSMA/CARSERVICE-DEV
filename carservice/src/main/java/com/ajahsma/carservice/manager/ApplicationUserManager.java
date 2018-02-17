package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.ApplicationUserDTO;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.model.ApplicationUserTO;

/**
 * @author SHARAN A
 */

public interface ApplicationUserManager extends DefaultManager {

	JsonResponse login(ApplicationUserDTO applicationUserDTO, String urlType);

	ApplicationUserTO findByUserName(String string);

}
