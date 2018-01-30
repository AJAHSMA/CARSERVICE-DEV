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
import com.ajahsma.carservice.manager.ItemManager;
import com.ajahsma.carservice.model.ItemTO;

/**
 * @author SHARAN A
 */

@Controller
public class ItemController extends AbstractController {

	@Autowired
	private ItemManager itemManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.itemManager;
	}
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			ItemTO item = (ItemTO) getDefaultManager().getDomain(ItemTO.class, id);
			getDefaultManager().deleteDomain(item);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateItem", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody ItemTO item) {
		try {
			getDefaultManager().updateDomain(item);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody ItemTO item) {
		getDefaultManager().saveDomain(item);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<ItemTO> items) {
		if (!CollectionUtils.isEmpty(items)) {
			for (ItemTO item : items) {
				getDefaultManager().saveDomain(item);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllItems")
	@ResponseBody
	public List<ItemTO> getAllItems() {
		return (List) getDefaultManager().getAllDomain(ItemTO.class);
	}
}