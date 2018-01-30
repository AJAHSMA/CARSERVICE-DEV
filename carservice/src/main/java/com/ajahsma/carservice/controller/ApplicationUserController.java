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

import com.ajahsma.carservice.manager.ApplicationUserManager;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.model.ApplicationUserTO;

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
	
	@RequestMapping(value = "/deleteAapplicationUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			ApplicationUserTO applicationUser = (ApplicationUserTO) getDefaultManager().getDomain(ApplicationUserTO.class, id);
			getDefaultManager().deleteDomain(applicationUser);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateAapplicationUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody ApplicationUserTO applicationUser) {
		try {
			getDefaultManager().updateDomain(applicationUser);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveAapplicationUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody ApplicationUserTO applicationUser) {
		getDefaultManager().saveDomain(applicationUser);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllAapplicationUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<ApplicationUserTO> applicationUsers) {
		if (!CollectionUtils.isEmpty(applicationUsers)) {
			for (ApplicationUserTO applicationUser : applicationUsers) {
				getDefaultManager().saveDomain(applicationUser);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllAapplicationUsers")
	@ResponseBody
	public List<ApplicationUserTO> getAllApplicationUsers() {
		return (List) getDefaultManager().getAllDomain(ApplicationUserTO.class);
	}
}