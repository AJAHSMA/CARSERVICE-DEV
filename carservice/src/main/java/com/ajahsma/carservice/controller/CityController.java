package com.ajahsma.carservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.dto.CityDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.CityManager;
import com.ajahsma.carservice.model.CityTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "/carservice")
public class CityController extends AbstractController {

	@Autowired
	private CityManager cityManager;

	private static final String[] NESTED_PATHS_TO_INITIALIZE = new String[] {"state.country"};

	@Override
	protected CityManager getDefaultManager() {
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
	JsonResponse save(@RequestBody CityDTO cityDTO) {
		String urlType = "carservice/saveCity";

		Map<String, Object> items = new HashMap<>();
		try {

			CityTO cityTO = getDefaultManager().convertCityDTOToCityTO(cityDTO);

			saveDomain(cityTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "City"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
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
	public List<CityTO> getAllCities(@RequestParam(value = "isLoadLazy", required = false) Boolean isLoadLazy) {
		setIsLoadLazy(isLoadLazy);
		List<CityTO> cities = (List) getAllDomains(CityTO.class, getNestedPathsToInitializeForServiceFind()); 
		
		return cities;
	}
	
	
	@Override
	protected String[] getNestedPathsToInitializeForServiceFind() {
		return NESTED_PATHS_TO_INITIALIZE;
	}


	
	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		CityTO cityTO = (CityTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(cityTO.getCode())) {
			validateSuccess = false;
			stringBuilder.append("Code is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(cityTO.getDescription())) {
			validateSuccess = false;
			stringBuilder.append("Description mandatory").append(", ");
		}
		if (cityTO.getState() == null) {
			validateSuccess = false;
			stringBuilder.append("State is mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}