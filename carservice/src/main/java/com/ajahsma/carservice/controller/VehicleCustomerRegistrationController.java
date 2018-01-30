package com.ajahsma.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;

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
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleCustomerRegistrationTO vehicleCustomerRegistration = (VehicleCustomerRegistrationTO) getDefaultManager().getDomain(VehicleCustomerRegistrationTO.class, id);
			getDefaultManager().deleteDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateVehicleCustomerRegistration", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody VehicleCustomerRegistrationTO vehicleCustomerRegistration) {
		try {
			getDefaultManager().updateDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveVehicleCustomerRegistration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody VehicleCustomerRegistrationTO vehicleCustomerRegistration) {
		getDefaultManager().saveDomain(vehicleCustomerRegistration);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllVehicleCustomerRegistrations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<VehicleCustomerRegistrationTO> vehicleCustomerRegistrations) {
		if (!CollectionUtils.isEmpty(vehicleCustomerRegistrations)) {
			for (VehicleCustomerRegistrationTO vehicleCustomerRegistration : vehicleCustomerRegistrations) {
				getDefaultManager().saveDomain(vehicleCustomerRegistration);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllVehicleCustomerRegistrations")
	@ResponseBody
	public List<VehicleCustomerRegistrationTO> getAllVehicleCustomerRegistrations() {
		return (List) getDefaultManager().getAllDomain(VehicleCustomerRegistrationTO.class);
	}
}