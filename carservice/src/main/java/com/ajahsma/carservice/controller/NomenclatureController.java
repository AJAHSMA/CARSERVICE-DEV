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
import com.ajahsma.carservice.manager.NomenclatureManager;
import com.ajahsma.carservice.model.NomenclatureTO;

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
	public String delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			NomenclatureTO nomenclature = (NomenclatureTO) getDefaultManager().getDomain(NomenclatureTO.class, id);
			getDefaultManager().deleteDomain(nomenclature);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/updateNomenclature", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody NomenclatureTO nomenclature) {
		try {
			getDefaultManager().updateDomain(nomenclature);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/saveNomenclature", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody NomenclatureTO nomenclature) {
		getDefaultManager().saveDomain(nomenclature);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAllNomenclatures", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<NomenclatureTO> nomenclatures) {
		if (!CollectionUtils.isEmpty(nomenclatures)) {
			for (NomenclatureTO nomenclature : nomenclatures) {
				getDefaultManager().saveDomain(nomenclature);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAllNomenclatures")
	@ResponseBody
	public List<NomenclatureTO> getAllNomenclatures() {
		return (List) getDefaultManager().getAllDomain(NomenclatureTO.class);
	}
}