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

import com.ajahsma.carservice.manager.CarModelManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CarModelTO;

/**
 * @author SHARAN A
 */

@Controller
public class ModelController extends AbstractController {

	@Autowired
	private CarModelManager carModelManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.carModelManager;
	}
	
	@RequestMapping(value = "/deleteCarModel", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CarModelTO carModel = (CarModelTO) getDefaultManager().getDomain(CarModelTO.class, id);
			getDefaultManager().deleteDomain(carModel);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateCarModel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody CarModelTO carModel) {
		try {
			getDefaultManager().updateDomain(carModel);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveCarModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody CarModelTO carModel) {
		getDefaultManager().saveDomain(carModel);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllCarModels", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<CarModelTO> carModels) {
		if (!CollectionUtils.isEmpty(carModels)) {
			for (CarModelTO carModel : carModels) {
				getDefaultManager().saveDomain(carModel);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllCarModels")
	@ResponseBody
	public List<CarModelTO> getAllCarModels() {
		return (List) getDefaultManager().getAllDomain(CarModelTO.class);
	}
}