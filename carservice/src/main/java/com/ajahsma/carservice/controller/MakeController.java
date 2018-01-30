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

import com.ajahsma.carservice.manager.CarMakeManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CarMakeTO;

/**
 * @author SHARAN A
 */

@Controller
public class MakeController extends AbstractController {

	@Autowired
	private CarMakeManager carMakeManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.carMakeManager;
	}
	
	@RequestMapping(value = "/deleteCarMake", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CarMakeTO carMake = (CarMakeTO) getDefaultManager().getDomain(CarMakeTO.class, id);
			getDefaultManager().deleteDomain(carMake);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateCarMake", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody CarMakeTO carMake) {
		try {
			getDefaultManager().updateDomain(carMake);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveCarMake", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody CarMakeTO carMake) {
		getDefaultManager().saveDomain(carMake);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllCarMakes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<CarMakeTO> carMakes) {
		if (!CollectionUtils.isEmpty(carMakes)) {
			for (CarMakeTO carMake : carMakes) {
				getDefaultManager().saveDomain(carMake);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllCarMakes")
	@ResponseBody
	public List<CarMakeTO> getAllCarMakes() {
		return (List) getDefaultManager().getAllDomain(CarMakeTO.class);
	}
}