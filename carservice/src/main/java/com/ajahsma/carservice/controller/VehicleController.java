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

import com.ajahsma.carservice.model.Vehicle;
import com.ajahsma.carservice.service.VehicleManager;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.VehicleManager;

/**
 * @author SHARAN A
 */

@Controller("vehicleController")
@RequestMapping(value = "/vehicle")
public class VehicleController extends AbstractController {

	@Autowired
	private VehicleManager vehicleManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.vehicleManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Vehicle vehicle = (Vehicle) getDefaultManager().getDomain(Vehicle.class, id);
			getDefaultManager().deleteDomain(vehicle);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Vehicle vehicle) {
		try {
			getDefaultManager().updateDomain(vehicle);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Vehicle vehicle) {
		getDefaultManager().saveDomain(vehicle);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Vehicle> vehicles) {
		if (!CollectionUtils.isEmpty(vehicles)) {
			for (Vehicle vehicle : vehicles) {
				getDefaultManager().saveDomain(vehicle);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Vehicle> getAllVehicles() {
		return (List) getDefaultManager().getAllDomain(Vehicle.class);
	}
}