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
import com.ajahsma.carservice.manager.CityManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CityTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

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
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CityTO city = (CityTO) getDefaultManager().getDomain(CityTO.class, id);
			getDefaultManager().deleteDomain(city);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
		}

	@RequestMapping(value = "/updateCity", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody CityTO city) {
		try {
			getDefaultManager().updateDomain(city);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody CityTO city) {
		getDefaultManager().saveDomain(city);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllCities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<CityTO> citys) {
		if (!CollectionUtils.isEmpty(citys)) {
			for (CityTO city : citys) {
				getDefaultManager().saveDomain(city);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CityTO> getAllCitys() {
		return (List) getDefaultManager().getAllDomain(CityTO.class);
	}
}