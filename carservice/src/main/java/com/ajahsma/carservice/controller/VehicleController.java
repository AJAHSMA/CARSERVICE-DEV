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
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.VehicleTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

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
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleTO vehicle = (VehicleTO) getDefaultManager().getDomain(VehicleTO.class, id);
			getDefaultManager().deleteDomain(vehicle);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody VehicleTO vehicle) {
		try {
			getDefaultManager().updateDomain(vehicle);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody VehicleTO vehicle) {
		getDefaultManager().saveDomain(vehicle);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllVehicles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<VehicleTO> vehicles) {
		if (!CollectionUtils.isEmpty(vehicles)) {
			for (VehicleTO vehicle : vehicles) {
				getDefaultManager().saveDomain(vehicle);
			}
		}
		
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllVehicles")
	@ResponseBody
	public List<VehicleTO> getAllVehicles() {
		return (List) getDefaultManager().getAllDomain(VehicleTO.class);
	}
}