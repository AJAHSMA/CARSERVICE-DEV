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

import com.ajahsma.carservice.model.ApplicationUser;
import com.ajahsma.carservice.service.ApplicationUserManager;
import com.ajahsma.carservice.service.DefaultManager;

/**
 * @author SHARAN A
 */

@Controller("applicationUserController")
@RequestMapping(value = "/applicationUser")
public class ApplicationUserController extends AbstractController {

	@Autowired
	private ApplicationUserManager applicationUserManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.applicationUserManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			ApplicationUser applicationUser = (ApplicationUser) getDefaultManager().getDomain(ApplicationUser.class, id);
			getDefaultManager().deleteDomain(applicationUser);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody ApplicationUser applicationUser) {
		try {
			getDefaultManager().updateDomain(applicationUser);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody ApplicationUser applicationUser) {
		getDefaultManager().saveDomain(applicationUser);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<ApplicationUser> applicationUsers) {
		if (!CollectionUtils.isEmpty(applicationUsers)) {
			for (ApplicationUser applicationUser : applicationUsers) {
				getDefaultManager().saveDomain(applicationUser);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<ApplicationUser> getAllApplicationUsers() {
		return (List) getDefaultManager().getAllDomain(ApplicationUser.class);
	}
}