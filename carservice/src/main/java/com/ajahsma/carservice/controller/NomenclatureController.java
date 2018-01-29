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

import com.ajahsma.carservice.model.Nomenclature;
import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.NomenclatureManager;

/**
 * @author SHARAN A
 */

@Controller("nomenclatureController")
@RequestMapping(value = "/nomenclature")
public class NomenclatureController extends AbstractController {

	@Autowired
	private NomenclatureManager nomenclatureManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.nomenclatureManager;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			Nomenclature nomenclature = (Nomenclature) getDefaultManager().getDomain(Nomenclature.class, id);
			getDefaultManager().deleteDomain(nomenclature);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String update(@RequestBody Nomenclature nomenclature) {
		try {
			getDefaultManager().updateDomain(nomenclature);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Data succesfully deleted!";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String save(@RequestBody Nomenclature nomenclature) {
		getDefaultManager().saveDomain(nomenclature);
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveAll(@RequestBody List<Nomenclature> nomenclatures) {
		if (!CollectionUtils.isEmpty(nomenclatures)) {
			for (Nomenclature nomenclature : nomenclatures) {
				getDefaultManager().saveDomain(nomenclature);
			}
		}
		
		return "Data succesfully saved!";
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Nomenclature> getAllNomenclatures() {
		return (List) getDefaultManager().getAllDomain(Nomenclature.class);
	}
}