package com.ajahsma.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.model.VehicleCustomerRegistration;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.VehicleCustomerRegistrationManager;

/**
 * @author SHARAN A
 */

@Controller("vehicleCustomerRegistrationController")
@RequestMapping(value = "/vehicleCustomerRegistration")
public class VehicleCustomerRegistrationController extends AbstractController {

	@Autowired
	private VehicleCustomerRegistrationManager vehicleCustomerRegistrationManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.vehicleCustomerRegistrationManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			VehicleCustomerRegistration vehicleCustomerRegistration = (VehicleCustomerRegistration) getDefaultManager().getDomain(VehicleCustomerRegistration.class, id);
			getDefaultManager().deleteDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody VehicleCustomerRegistration vehicleCustomerRegistration) {
		try {
			getDefaultManager().updateDomain(vehicleCustomerRegistration);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody VehicleCustomerRegistration vehicleCustomerRegistration) {
		getDefaultManager().saveDomain(vehicleCustomerRegistration);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<VehicleCustomerRegistration> vehicleCustomerRegistrations) {
		if (!CollectionUtils.isEmpty(vehicleCustomerRegistrations)) {
			for (VehicleCustomerRegistration vehicleCustomerRegistration : vehicleCustomerRegistrations) {
				getDefaultManager().saveDomain(vehicleCustomerRegistration);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<VehicleCustomerRegistration> getAllVehicleCustomerRegistrations() {
		return (List) getDefaultManager().getAllDomain(VehicleCustomerRegistration.class);
	}
}