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

import com.ajahsma.carservice.dto.CountryDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.CountryManager;
import com.ajahsma.carservice.model.CountryTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "/carservice")
public class CountryController extends AbstractController {

	@Autowired
	private CountryManager countryManager;

	@Override
	protected CountryManager getDefaultManager() {
		return this.countryManager;
	}

	@RequestMapping(value = "/deleteCountry", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CountryTO country = (CountryTO) getDefaultManager().getDomain(CountryTO.class, id);
			getDefaultManager().deleteDomain(country);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody CountryTO country) {
		try {
			getDefaultManager().updateDomain(country);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveCountry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody CountryDTO countryDto) {
		
		String urlType = "carservice/saveCountry";

		Map<String, Object> items = new HashMap<>();
		try {

			CountryTO countryTO = getDefaultManager().convertCountryDTOToCountryTO(countryDto);

			saveDomain(countryTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Country"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);

	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<CountryTO> countrys) {
		if (!CollectionUtils.isEmpty(countrys)) {
			for (CountryTO country : countrys) {
				getDefaultManager().saveDomain(country);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<CountryTO> getAllCountries() {
		return (List) getDefaultManager().getAllDomain(CountryTO.class);
	}

	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		CountryTO countryTO = (CountryTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(countryTO.getCode())) {
			validateSuccess = false;
			stringBuilder.append("Code is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(countryTO.getDescription())) {
			validateSuccess = false;
			stringBuilder.append("Description no mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}