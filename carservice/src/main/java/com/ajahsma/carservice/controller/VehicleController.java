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
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.VehicleTO;

/**
 * @author SHARAN A
 */

@Controller
public class VehicleController extends AbstractController {

	@Autowired
	private VehicleManager vehicleManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.vehicleManager;
	}
	
	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleTO vehicle = (VehicleTO) getDefaultManager().getDomain(VehicleTO.class, id);
			getDefaultManager().deleteDomain(vehicle);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody VehicleTO vehicle) {
		try {
			getDefaultManager().updateDomain(vehicle);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody VehicleTO vehicle) {
		getDefaultManager().saveDomain(vehicle);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllVehicles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<VehicleTO> vehicles) {
		if (!CollectionUtils.isEmpty(vehicles)) {
			for (VehicleTO vehicle : vehicles) {
				getDefaultManager().saveDomain(vehicle);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllVehicles")
	@ResponseBody
	public List<VehicleTO> getAllVehicles() {
		return (List) getDefaultManager().getAllDomain(VehicleTO.class);
	}
}