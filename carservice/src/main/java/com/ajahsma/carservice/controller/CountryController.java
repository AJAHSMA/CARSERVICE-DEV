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

import com.ajahsma.carservice.model.Country;
import com.ajahsma.carservice.service.CountryManager;
import com.ajahsma.carservice.service.DefaultManager;

/**
 * @author SHARAN A
 */

@Controller("countryController")
@RequestMapping(value = "/country")
public class CountryController extends AbstractController {

	@Autowired
	private CountryManager countryManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.countryManager;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) long id) {
		try {
			Country country = (Country) getDefaultManager().getDomain(Country.class, id);
			getDefaultManager().deleteDomain(country);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Country country) {
		try {
			getDefaultManager().updateDomain(country);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Country country) {
		getDefaultManager().saveDomain(country);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Country> countrys) {
		if (!CollectionUtils.isEmpty(countrys)) {
			for (Country country : countrys) {
				getDefaultManager().saveDomain(country);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Country> getAllCountries() {
		return (List) getDefaultManager().getAllDomain(Country.class);
	}
}