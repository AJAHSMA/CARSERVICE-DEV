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

import com.ajahsma.carservice.model.Employee;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.EmployeeManager;

/**
 * @author SHARAN A
 */

@Controller("employeeController")
@RequestMapping(value = "/employee")
public class EmployeeController extends AbstractController {

	@Autowired
	private EmployeeManager employeeManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.employeeManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Employee employee = (Employee) getDefaultManager().getDomain(Employee.class, id);
			getDefaultManager().deleteDomain(employee);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Employee employee) {
		try {
			getDefaultManager().updateDomain(employee);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Employee employee) {
		getDefaultManager().saveDomain(employee);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Employee> employees) {
		if (!CollectionUtils.isEmpty(employees)) {
			for (Employee employee : employees) {
				getDefaultManager().saveDomain(employee);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return (List) getDefaultManager().getAllDomain(Employee.class);
	}
}