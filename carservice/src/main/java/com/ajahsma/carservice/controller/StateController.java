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

import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.StateManager;
import com.ajahsma.carservice.model.StateTO;

/**
 * @author SHARAN A
 */

@Controller
public class StateController extends AbstractController {

	@Autowired
	private StateManager stateManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.stateManager;
	}
	
	@RequestMapping(value = "/deleteState", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			StateTO state = (StateTO) getDefaultManager().getDomain(StateTO.class, id);
			getDefaultManager().deleteDomain(state);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateState", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody StateTO state) {
		try {
			getDefaultManager().updateDomain(state);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveState", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody StateTO state) {
		getDefaultManager().saveDomain(state);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllStates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<StateTO> states) {
		if (!CollectionUtils.isEmpty(states)) {
			for (StateTO state : states) {
				getDefaultManager().saveDomain(state);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllStates")
	@ResponseBody
	public List<StateTO> getAllStates() {
		return (List) getDefaultManager().getAllDomain(StateTO.class);
	}
}