package com.ajahsma.carservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.DesignationManager;
import com.ajahsma.carservice.model.DesignationTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
@RequestMapping(value = "/carservice")
public class DesignationController extends AbstractController {

	@Autowired
	private DesignationManager designationManager;

	@Override
	protected DefaultManager getDefaultManager() {
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
	JsonResponse save(@RequestBody DesignationTO designation) {
		saveDomain(designation);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
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

}