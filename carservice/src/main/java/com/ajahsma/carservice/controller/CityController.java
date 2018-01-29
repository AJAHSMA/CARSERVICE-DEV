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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.model.City;
import com.ajahsma.carservice.service.CityManager;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.CityManager;

/**
 * @author SHARAN A
 */

@Controller("cityController")
@RequestMapping(value = "/city")
public class CityController extends AbstractController {

	@Autowired
	private CityManager cityManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.cityManager;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) long id) {
		try {
			City city = (City) getDefaultManager().getDomain(City.class, id);
			getDefaultManager().deleteDomain(city);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody City city) {
		try {
			getDefaultManager().updateDomain(city);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody City city) {
		getDefaultManager().saveDomain(city);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<City> citys) {
		if (!CollectionUtils.isEmpty(citys)) {
			for (City city : citys) {
				getDefaultManager().saveDomain(city);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<City> getAllCitys() {
		return (List) getDefaultManager().getAllDomain(City.class);
	}
}