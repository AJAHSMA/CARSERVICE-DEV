package com.ajahsma.carservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.dto.VehicleCustomerRegistrationDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "/carservice")
public class VehicleCustomerRegistrationController extends AbstractController {

	@Autowired
	private VehicleCustomerRegistrationManager vehicleCustomerRegistrationManager;

	@Override
	protected VehicleCustomerRegistrationManager getDefaultManager() {
		return this.vehicleCustomerRegistrationManager;
	}

	@RequestMapping(value = "/deleteVehicleCustomerRegistration", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleCustomerRegistrationTO vehicleCustomerRegistration = (VehicleCustomerRegistrationTO) getDefaultManager()
					.getDomain(VehicleCustomerRegistrationTO.class, id);
			getDefaultManager().deleteDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateVehicleCustomerRegistration", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody VehicleCustomerRegistrationTO vehicleCustomerRegistration) {
		try {
			getDefaultManager().updateDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveVehicleCustomerRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody VehicleCustomerRegistrationDTO vehicleCustomerRegistrationDto) {

		String urlType = "carservice/saveVehicleCustomerRegistration";

		Map<String, Object> items = new HashMap<>();
		try {

			VehicleCustomerRegistrationTO vehicleCustomerRegistrationTO = getDefaultManager().convertVehicleDTOToVehocleTO(vehicleCustomerRegistrationDto);

			saveDomain(vehicleCustomerRegistrationTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Registration"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
	}

	@RequestMapping(value = "/saveAllVehicleCustomerRegistrations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<VehicleCustomerRegistrationTO> vehicleCustomerRegistrations) {
		if (!CollectionUtils.isEmpty(vehicleCustomerRegistrations)) {
			for (VehicleCustomerRegistrationTO vehicleCustomerRegistration : vehicleCustomerRegistrations) {
				getDefaultManager().saveDomain(vehicleCustomerRegistration);
			}
		}

		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllVehicleCustomerRegistrations")
	@ResponseBody
	public List<VehicleCustomerRegistrationTO> getAllVehicleCustomerRegistrations() {
		return (List) getDefaultManager().getAllDomain(VehicleCustomerRegistrationTO.class);
	}
	
	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		VehicleCustomerRegistrationTO vehicleCustomerRegistrationTO = (VehicleCustomerRegistrationTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (vehicleCustomerRegistrationTO.getVehicle() == null) {
			validateSuccess = false;
			stringBuilder.append("Vehicle is mandatory").append(", ");
		}
		if (vehicleCustomerRegistrationTO.getCustomer() == null) {
			validateSuccess = false;
			stringBuilder.append("Customer is mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}