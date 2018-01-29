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

import com.ajahsma.carservice.model.Designation;
import com.ajahsma.carservice.service.DesignationManager;

/**
 * @author SHARAN A
 */

@Controller("designationController")
@RequestMapping(value = "/designation")
public class DesignationController {

	@Autowired
	private DesignationManager designationManager;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Designation designation = (Designation) designationManager.getDomain(Designation.class, id);
			designationManager.deleteDomain(designation);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Person succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Designation designation) {
		try {
			designationManager.updateDomain(designation);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Designation succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Designation designation) {
		designationManager.saveDomain(designation);
		return "Person succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String saveAll(@RequestBody List<Designation> designations) {
		if (!CollectionUtils.isEmpty(designations)) {
			for (Designation designation : designations) {
				designationManager.saveDomain(designation);
			}
		}
		
		return "Person succesfully saved!";
	}

	@RequestMapping(value = "/allDesignations")
	@ResponseBody
	public List<Designation> getAllDesignations() {
		return (List) designationManager.getAllDomain(Designation.class);
	}
}