package com.ajahsma.carservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.dto.DesignationDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.DesignationManager;
import com.ajahsma.carservice.model.DesignationTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "/carservice")
public class DesignationController extends AbstractController {

	@Autowired
	private DesignationManager designationManager;

	@Override
	protected DesignationManager getDefaultManager() {
		return this.designationManager;
	}

	@RequestMapping(value = "/deleteDesignation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			deleteDomain(DesignationTO.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateDesignation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody DesignationTO designation) {
		try {
			updateDomain(designation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveDesignation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody DesignationDTO designationDto) {
		String urlType = "carservice/saveDesignation";

		Map<String, Object> items = new HashMap<>();
		try {

			DesignationTO designationTO = getDefaultManager().convertDesignationDTOToDesignationTO(designationDto);

			saveDomain(designationTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Designation"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
	}

	@RequestMapping(value = "/saveAllDesignations", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<DesignationTO> designations) {

		saveAll(designations);

		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllDesignations")
	@ResponseBody
	public List<DesignationTO> getAllDesignations() {
		return (List<DesignationTO>) getAllDomains(DesignationTO.class);
	}



	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		DesignationTO designationTO = (DesignationTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(designationTO.getCode())) {
			validateSuccess = false;
			stringBuilder.append("Code is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(designationTO.getDescription())) {
			validateSuccess = false;
			stringBuilder.append("Description is mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
}