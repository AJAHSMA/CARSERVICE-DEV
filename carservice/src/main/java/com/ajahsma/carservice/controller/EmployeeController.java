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

import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.EmployeeManager;
import com.ajahsma.carservice.model.EmployeeTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
@RequestMapping(value = "/carservice")
public class EmployeeController extends AbstractController {

	@Autowired
	private EmployeeManager employeeManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.employeeManager;
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			EmployeeTO employee = (EmployeeTO) getDefaultManager().getDomain(EmployeeTO.class, id);
			getDefaultManager().deleteDomain(employee);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody EmployeeTO employee) {
		try {
			getDefaultManager().updateDomain(employee);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody EmployeeTO employee) {
		getDefaultManager().saveDomain(employee);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllEmployees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<EmployeeTO> employees) {
		if (!CollectionUtils.isEmpty(employees)) {
			for (EmployeeTO employee : employees) {
				getDefaultManager().saveDomain(employee);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllEmployees")
	@ResponseBody
	public List<EmployeeTO> getAllEmployees() {
		return (List) getDefaultManager().getAllDomain(EmployeeTO.class);
	}
}