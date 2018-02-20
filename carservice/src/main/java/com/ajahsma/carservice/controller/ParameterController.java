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

import com.ajahsma.carservice.dto.ParameterDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.ParameterManager;
import com.ajahsma.carservice.model.ParameterTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "/carservice")
public class ParameterController extends AbstractController {

	@Autowired
	private ParameterManager parameterManager;

	@Override
	protected ParameterManager getDefaultManager() {
		return this.parameterManager;
	}

	@RequestMapping(value = "/deleteParameter", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			ParameterTO parameter = (ParameterTO) getDefaultManager().getDomain(ParameterTO.class, id);
			getDefaultManager().deleteDomain(parameter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody ParameterTO parameter) {
		try {
			getDefaultManager().updateDomain(parameter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveParameter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody ParameterDTO parameterDto) {
		
		String urlType = "carservice/saveParameter";

		Map<String, Object> items = new HashMap<>();
		try {

			ParameterTO parameterTO = getDefaultManager().convertParameterDTOToParameterTO(parameterDto);

			saveDomain(parameterTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Parameter"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);

	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<ParameterTO> parameters) {
		if (!CollectionUtils.isEmpty(parameters)) {
			for (ParameterTO parameter : parameters) {
				getDefaultManager().saveDomain(parameter);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<ParameterTO> getAllCountries() {
		return (List) getDefaultManager().getAllDomain(ParameterTO.class);
	}

	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		ParameterTO parameterTO = (ParameterTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(parameterTO.getApplicationId())) {
			validateSuccess = false;
			stringBuilder.append("Application iId is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(parameterTO.getValue())) {
			validateSuccess = false;
			stringBuilder.append("Value no mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}