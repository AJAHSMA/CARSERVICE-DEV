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
import com.ajahsma.carservice.manager.ApplicationUserManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.ApplicationUserTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
public class ApplicationUserController extends AbstractController {

	@Autowired
	private ApplicationUserManager applicationUserManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.applicationUserManager;
	}
	
	@RequestMapping(value = "/saveAapplicationUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody ApplicationUserTO applicationUser) {
		getDefaultManager().saveDomain(applicationUser);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllAapplicationUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<ApplicationUserTO> applicationUsers) {
		if (!CollectionUtils.isEmpty(applicationUsers)) {
			for (ApplicationUserTO applicationUser : applicationUsers) {
				getDefaultManager().saveDomain(applicationUser);
			}
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllAapplicationUsers")
	@ResponseBody
	public List<ApplicationUserTO> getAllApplicationUsers() {
		return (List) getDefaultManager().getAllDomain(ApplicationUserTO.class);
	}
	
	@RequestMapping(value = "/updateAapplicationUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody ApplicationUserTO applicationUser) {
		try {
			getDefaultManager().updateDomain(applicationUser);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}
	
	@RequestMapping(value = "/deleteAapplicationUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			ApplicationUserTO applicationUser = (ApplicationUserTO) getDefaultManager().getDomain(ApplicationUserTO.class, id);
			getDefaultManager().deleteDomain(applicationUser);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	

	
}