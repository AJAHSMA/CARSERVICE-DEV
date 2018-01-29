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

import com.ajahsma.carservice.model.State;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.StateManager;

/**
 * @author SHARAN A
 */

@Controller("stateController")
@RequestMapping(value = "/state")
public class StateController extends AbstractController {

	@Autowired
	private StateManager stateManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.stateManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			State state = (State) getDefaultManager().getDomain(State.class, id);
			getDefaultManager().deleteDomain(state);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody State state) {
		try {
			getDefaultManager().updateDomain(state);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody State state) {
		getDefaultManager().saveDomain(state);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<State> states) {
		if (!CollectionUtils.isEmpty(states)) {
			for (State state : states) {
				getDefaultManager().saveDomain(state);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<State> getAllStates() {
		return (List) getDefaultManager().getAllDomain(State.class);
	}
}