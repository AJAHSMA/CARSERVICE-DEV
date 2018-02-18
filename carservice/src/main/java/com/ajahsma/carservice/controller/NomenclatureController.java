package com.ajahsma.carservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.dto.NomenclatureDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.NomenclatureManager;
import com.ajahsma.carservice.model.NomenclatureTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "/carservice")
public class NomenclatureController extends AbstractController {

	@Autowired
	private NomenclatureManager nomenclatureManager;

	@Override
	protected NomenclatureManager getDefaultManager() {
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
	JsonResponse save(@RequestBody NomenclatureDTO nomenclatureDto) {
		String urlType = "carservice/saveNomenclature";

		Map<String, Object> items = new HashMap<>();
		try {

			NomenclatureTO nomenclatureTO = getDefaultManager().convertNomenclatureDTOToNomenclatureTO(nomenclatureDto);

			saveDomain(nomenclatureTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Nomenclature"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);
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

	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		NomenclatureTO nomenclatureTO = (NomenclatureTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(nomenclatureTO.getCode())) {
			validateSuccess = false;
			stringBuilder.append("Code is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(nomenclatureTO.getName())) {
			validateSuccess = false;
			stringBuilder.append("Name is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(nomenclatureTO.getCheckType())) {
			validateSuccess = false;
			stringBuilder.append("Check type is mandatory").append(", ");
		}
		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}
	}
	
}