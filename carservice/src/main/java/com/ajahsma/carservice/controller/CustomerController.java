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

import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.CustomerTO;

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

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody CustomerTO customer) {
		getDefaultManager().saveDomain(customer);
		return "Data succesfully saved!";
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
	public List<CustomerTO> getAllCustomers() {
		return (List) getDefaultManager().getAllDomain(CustomerTO.class);
	}
}