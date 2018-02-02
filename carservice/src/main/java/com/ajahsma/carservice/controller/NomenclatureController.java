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
import com.ajahsma.carservice.manager.NomenclatureManager;
import com.ajahsma.carservice.model.NomenclatureTO;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
public class NomenclatureController extends AbstractController {

	@Autowired
	private NomenclatureManager nomenclatureManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.nomenclatureManager;
	}
	
	@RequestMapping(value = "/deleteNomenclature", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			NomenclatureTO nomenclature = (NomenclatureTO) getDefaultManager().getDomain(NomenclatureTO.class, id);
			getDefaultManager().deleteDomain(nomenclature);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateNomenclature", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody NomenclatureTO nomenclature) {
		try {
			getDefaultManager().updateDomain(nomenclature);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveNomenclature", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody NomenclatureTO nomenclature) {
		getDefaultManager().saveDomain(nomenclature);
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveAllNomenclatures", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<NomenclatureTO> nomenclatures) {
		if (!CollectionUtils.isEmpty(nomenclatures)) {
			for (NomenclatureTO nomenclature : nomenclatures) {
				getDefaultManager().saveDomain(nomenclature);
			}
		}
		
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllNomenclatures")
	@ResponseBody
	public List<NomenclatureTO> getAllNomenclatures() {
		return (List) getDefaultManager().getAllDomain(NomenclatureTO.class);
	}
}