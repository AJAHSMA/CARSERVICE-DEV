package com.ajahsma.carservice.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.ItemDao;
import com.ajahsma.carservice.dto.ItemDTO;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.ItemManager;
import com.ajahsma.carservice.model.Domain;
import com.ajahsma.carservice.model.ItemTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Service
public class ItemManagerImpl extends DefaultManagerImpl implements ItemManager {

	@Autowired
	public void setDefaultDao(ItemDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private ItemDao getItemDao() {
		return (ItemDao) getDefaultDao();
	}

	@Override
	public JsonResponse getAllNomenclature(String urlType) {
		// TODO Auto-generated method stub
		List<Domain> allDomain = getAllDomain(ItemTO.class);
		List<ItemDTO> itemDto = new ArrayList<>();
		Map<String, Object> items = new HashMap<>();
		if (CarServiceUtils.isListNotNullAndEmpty(allDomain)) {
			for (Domain domain : allDomain) {
				ItemDTO dto = new ItemDTO();
				ItemTO itemTO = (ItemTO) domain;
				dto.setId(itemTO.getId());
				dto.setName(itemTO.getName());
				itemDto.add(dto);
			}
		}

		if (CarServiceUtils.isListNotNullAndEmpty(itemDto)) {
			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.DATA_FOUND);
			items.put(JsonResponseMessage.DATA, itemDto);
		} else {
			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, JsonResponseMessage.NO_DATA_FOUND);
		}
		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
	}
}
