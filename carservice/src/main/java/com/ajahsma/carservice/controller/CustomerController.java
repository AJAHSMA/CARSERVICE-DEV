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

import com.ajahsma.carservice.dto.CustomerDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.Data;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.json.Jsonaapi;
import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.model.CustomerTO;
import com.ajahsma.carservice.model.Domain;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
@RequestMapping(value = "/carservice")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerManager customerManager;

	@Override
	protected CustomerManager getDefaultManager() {
		return this.customerManager;
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse save(@RequestBody CustomerDTO customerDto) {
		String urlType = "carservice/saveCustomer";

		Map<String, Object> items = new HashMap<>();
		try {

			CustomerTO customerTO = getDefaultManager().convertCustomerDTOToCustomerTO(customerDto);

			saveDomain(customerTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Customer"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);

	}

	@RequestMapping(value = "/saveAllCustomers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<CustomerTO> customers) {
		if (!CollectionUtils.isEmpty(customers)) {
			for (CustomerTO customer : customers) {
				getDefaultManager().saveDomain(customer);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllCustomers")
	@ResponseBody
	public JsonResponse getAllCustomers() {
		List<Domain> allDomain = getDefaultManager().getAllDomain(CustomerTO.class);
		Data data = new Data();
		data.setItems(allDomain);
		data.setType("getAllCustomers");
		Jsonaapi jsonaapi = new Jsonaapi();
		jsonaapi.setVersion("1.0");
		JsonResponse jsonResponse = new JsonResponse(jsonaapi, data);
		return jsonResponse;

	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CustomerTO customer = (CustomerTO) getDefaultManager().getDomain(CustomerTO.class, id);
			getDefaultManager().deleteDomain(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody CustomerTO customer) {
		try {
			getDefaultManager().updateDomain(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		CustomerTO customerTO = (CustomerTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(customerTO.getName())) {
			validateSuccess = false;
			stringBuilder.append("Name is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getPhoneNo())) {
			validateSuccess = false;
			stringBuilder.append("Phone no mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getAddress())) {
			validateSuccess = false;
			stringBuilder.append("Address is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getCity())) {
			validateSuccess = false;
			stringBuilder.append("City is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getPincode())) {
			validateSuccess = false;
			stringBuilder.append("Pincode is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getIdProof())) {
			validateSuccess = false;
			stringBuilder.append("Id proof mandatory").append(", ");
		}
		if (StringUtils.isEmpty(customerTO.getIdProofNo())) {
			validateSuccess = false;
			stringBuilder.append("Id proof no mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}

}