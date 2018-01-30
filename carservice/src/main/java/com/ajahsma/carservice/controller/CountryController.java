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

import com.ajahsma.carservice.manager.CountryManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CountryTO;

/**
 * @author SHARAN A
 */

@Controller("countryController")
public class CountryController extends AbstractController {

	@Autowired
	private CountryManager countryManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.countryManager;
	}
	
	@RequestMapping(value = "/deleteCountry", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CountryTO country = (CountryTO) getDefaultManager().getDomain(CountryTO.class, id);
			getDefaultManager().deleteDomain(country);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody CountryTO country) {
		try {
			getDefaultManager().updateDomain(country);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody CountryTO country) {
		getDefaultManager().saveDomain(country);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<CountryTO> countrys) {
		if (!CollectionUtils.isEmpty(countrys)) {
			for (CountryTO country : countrys) {
				getDefaultManager().saveDomain(country);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<CountryTO> getAllCountries() {
		return (List) getDefaultManager().getAllDomain(CountryTO.class);
	}
}