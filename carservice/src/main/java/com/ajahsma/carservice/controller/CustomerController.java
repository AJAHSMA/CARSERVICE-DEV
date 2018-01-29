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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.model.Customer;
import com.ajahsma.carservice.service.CustomerManager;
import com.ajahsma.carservice.service.DefaultManager;

/**
 * @author SHARAN A
 */

@Controller("customerController")
@RequestMapping(value = "/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerManager customerManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.customerManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Customer customer = (Customer) getDefaultManager().getDomain(Customer.class, id);
			getDefaultManager().deleteDomain(customer);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Customer customer) {
		try {
			getDefaultManager().updateDomain(customer);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Customer customer) {
		getDefaultManager().saveDomain(customer);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Customer> customers) {
		if (!CollectionUtils.isEmpty(customers)) {
			for (Customer customer : customers) {
				getDefaultManager().saveDomain(customer);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Customer> getAllCustomers() {
		return (List) getDefaultManager().getAllDomain(Customer.class);
	}
}