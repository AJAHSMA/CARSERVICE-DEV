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

import com.ajahsma.carservice.model.Item;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.ItemManager;

/**
 * @author SHARAN A
 */

@Controller("itemController")
@RequestMapping(value = "/item")
public class ItemController extends AbstractController {

	@Autowired
	private ItemManager itemManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.itemManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Item item = (Item) getDefaultManager().getDomain(Item.class, id);
			getDefaultManager().deleteDomain(item);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Item item) {
		try {
			getDefaultManager().updateDomain(item);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Item item) {
		getDefaultManager().saveDomain(item);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Item> items) {
		if (!CollectionUtils.isEmpty(items)) {
			for (Item item : items) {
				getDefaultManager().saveDomain(item);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Item> getAllItems() {
		return (List) getDefaultManager().getAllDomain(Item.class);
	}
}