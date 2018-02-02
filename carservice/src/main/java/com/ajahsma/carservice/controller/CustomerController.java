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

import com.ajahsma.carservice.json.Data;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.Jsonaapi;
import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CustomerTO;
import com.ajahsma.carservice.model.Domain;

/**
 * @author SHARAN A
 */

@Controller
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerManager customerManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.customerManager;
	}
	
	
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse save(@RequestBody CustomerTO customer) {
		try {
			getDefaultManager().saveDomain(customer);
			Map<String, String> model = new HashMap<>();
			model.put("status", "success!");
			model.put("message", "Data succesfully saved!");
			Data data = new Data();
			data.setItems(model);
			data.setType("saveCustomer");
			Jsonaapi jsonaapi = new Jsonaapi();
			jsonaapi.setVersion("1.0");
			JsonResponse jsonResponse = new JsonResponse(jsonaapi, data);
			return jsonResponse;
		} catch (Exception e) {
			Map<String, String> model = new HashMap<>();
			model.put("status", "failure!");
			model.put("message", "error while saving!");
			Data data = new Data();
			data.setItems(model);
			Jsonaapi jsonaapi = new Jsonaapi();
			jsonaapi.setVersion("1.0");
			JsonResponse jsonResponse = new JsonResponse(jsonaapi, data);
			return jsonResponse;
		}

	}

	@RequestMapping(value = "/saveAllCustomers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<CustomerTO> customers) {
		if (!CollectionUtils.isEmpty(customers)) {
			for (CustomerTO customer : customers) {
				getDefaultManager().saveDomain(customer);
			}
		}
		
		return "Data succesfully saved!";
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
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			CustomerTO customer = (CustomerTO) getDefaultManager().getDomain(CustomerTO.class, id);
			getDefaultManager().deleteDomain(customer);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody CustomerTO customer) {
		try {
			getDefaultManager().updateDomain(customer);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	
}