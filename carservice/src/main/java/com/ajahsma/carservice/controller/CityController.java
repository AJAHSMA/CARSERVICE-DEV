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

import com.ajahsma.carservice.manager.CityManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CityTO;

/**
 * @author SHARAN A
 */

@Controller
public class CityController extends AbstractController {

	@Autowired
	private CityManager cityManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.cityManager;
	}
	
	@RequestMapping(value = "/deleteCity", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CityTO city = (CityTO) getDefaultManager().getDomain(CityTO.class, id);
			getDefaultManager().deleteDomain(city);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateCity", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody CityTO city) {
		try {
			getDefaultManager().updateDomain(city);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody CityTO city) {
		getDefaultManager().saveDomain(city);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllCities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<CityTO> citys) {
		if (!CollectionUtils.isEmpty(citys)) {
			for (CityTO city : citys) {
				getDefaultManager().saveDomain(city);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllCities")
	@ResponseBody
	public List<CityTO> getAllCitys() {
		return (List) getDefaultManager().getAllDomain(CityTO.class);
	}
}