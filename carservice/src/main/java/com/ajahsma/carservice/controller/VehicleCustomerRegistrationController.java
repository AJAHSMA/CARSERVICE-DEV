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

import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
public class VehicleCustomerRegistrationController extends AbstractController {

	@Autowired
	private VehicleCustomerRegistrationManager vehicleCustomerRegistrationManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.vehicleCustomerRegistrationManager;
	}
	
	@RequestMapping(value = "/deleteVehicleCustomerRegistration", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleCustomerRegistrationTO vehicleCustomerRegistration = (VehicleCustomerRegistrationTO) getDefaultManager().getDomain(VehicleCustomerRegistrationTO.class, id);
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
	JsonResponse save(@RequestBody VehicleCustomerRegistrationTO vehicleCustomerRegistration) {
		getDefaultManager().saveDomain(vehicleCustomerRegistration);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
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
}