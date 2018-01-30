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

import com.ajahsma.carservice.manager.DesignationManager;
import com.ajahsma.carservice.model.DesignationTO;

/**
 * @author SHARAN A
 */

@Controller
public class DesignationController {

	@Autowired
	private DesignationManager designationManager;

	@RequestMapping(value = "/deleteDesignation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			DesignationTO designation = (DesignationTO) designationManager.getDomain(DesignationTO.class, id);
			designationManager.deleteDomain(designation);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Person succesfully deleted!";
	}

	@RequestMapping(value = "/updateDesignation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody DesignationTO designation) {
		try {
			designationManager.updateDomain(designation);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Designation succesfully deleted!";
	}

	@RequestMapping(value = "/saveDesignation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody DesignationTO designation) {
		designationManager.saveDomain(designation);
		return "Person succesfully saved!";
	}

	@RequestMapping(value = "/saveAllDesignations", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String saveAll(@RequestBody List<DesignationTO> designations) {
		if (!CollectionUtils.isEmpty(designations)) {
			for (DesignationTO designation : designations) {
				designationManager.saveDomain(designation);
			}
		}
		
		return "Person succesfully saved!";
	}

	@RequestMapping(value = "/getAllDesignations")
	@ResponseBody
	public List<DesignationTO> getAllDesignations() {
		return (List) designationManager.getAllDomain(DesignationTO.class);
	}
}