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

import com.ajahsma.carservice.dto.ItemDTO;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.manager.DefaultManager;
import com.ajahsma.carservice.manager.ItemManager;
import com.ajahsma.carservice.model.ItemTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
@RequestMapping(value = "/carservice")
public class ItemController extends AbstractController {

	@Autowired
	private ItemManager itemManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.itemManager;
	}

	@RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			ItemTO item = (ItemTO) getDefaultManager().getDomain(ItemTO.class, id);
			getDefaultManager().deleteDomain(item);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateItem", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody ItemTO item) {
		try {
			getDefaultManager().updateDomain(item);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody ItemTO item) {
		getDefaultManager().saveDomain(item);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<ItemTO> items) {
		if (!CollectionUtils.isEmpty(items)) {
			for (ItemTO item : items) {
				getDefaultManager().saveDomain(item);
			}
		}

		Map<String, Object> item = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", item);
	}

	@RequestMapping(value = "/getAllNomenclatureItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse getAllItems() {
		// return (List) getDefaultManager().getAllDomain(ItemTO.class);
		String urlType = "/carservice/getAllItems";
		JsonResponse response = itemManager.getAllNomenclature(urlType);
		return response;
	}
}