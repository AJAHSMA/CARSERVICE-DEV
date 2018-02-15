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

import com.ajahsma.carservice.dto.VehicleDTO;
import com.ajahsma.carservice.enumeration.ErrorCodes;
import com.ajahsma.carservice.exception.ValidationFailureException;
import com.ajahsma.carservice.json.JsonResponse;
import com.ajahsma.carservice.json.JsonResponseMessage;
import com.ajahsma.carservice.manager.CarModelManager;
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.VehicleTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.ajahsma.carservice.utils.JSONHelperUtil;

/**
 * @author SHARAN A
 */

@Controller
@RequestMapping(value = "/carservice")
public class VehicleController extends AbstractController {

	@Autowired
	private VehicleManager vehicleManager;

	@Autowired
	private CarModelManager carModelManager;

	@Override
	protected VehicleManager getDefaultManager() {
		return this.vehicleManager;
	}

	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse delete(@RequestParam(value = "id", required = true) Long id) {
		try {
			VehicleTO vehicle = (VehicleTO) getDefaultManager().getDomain(VehicleTO.class, id);
			getDefaultManager().deleteDomain(vehicle);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse update(@RequestBody VehicleTO vehicle) {
		
		try {
			getDefaultManager().updateDomain(vehicle);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/saveVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse save(@RequestBody VehicleDTO vehicleDTO) {

		String urlType = "carservice/saveVehicle";

		Map<String, Object> items = new HashMap<>();
		try {

			VehicleTO vehicleTO = getDefaultManager().convertVehicleDTOToVehocleTO(vehicleDTO);

			saveDomain(vehicleTO);

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.SUCCESS);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.INFO_MESSAGE_CREATED_SUCCESSFULLY, "Vehicle"));

		} catch (Exception e) {

			items.put(JsonResponseMessage.STATUS, JsonResponseMessage.FAILURE);
			items.put(JsonResponseMessage.MESSAGE, CarServiceUtils.createMessage(JsonResponseMessage.EXCEPTION_MESSAGE, e.getMessage()));

		}

		return JSONHelperUtil.getJsonResponse("1.0", urlType, items);

	}

	@RequestMapping(value = "/saveAllVehicles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	JsonResponse saveAll(@RequestBody List<VehicleTO> vehicles) {
		if (!CollectionUtils.isEmpty(vehicles)) {
			for (VehicleTO vehicle : vehicles) {
				getDefaultManager().saveDomain(vehicle);
			}
		}

		Map<String, Object> items = new HashMap<>();
		return JSONHelperUtil.getJsonResponse("1.0", "", items);
	}

	@RequestMapping(value = "/getAllVehicles")
	@ResponseBody
	public List<VehicleTO> getAllVehicles() {
		return (List) getDefaultManager().getAllDomain(VehicleTO.class);
	}

	@Override
	protected void validateDomain() throws ValidationFailureException {
		boolean validateSuccess = true;

		VehicleTO vehicleTO = (VehicleTO) getDomain();
		StringBuilder stringBuilder = new StringBuilder("");

		if (StringUtils.isEmpty(vehicleTO.getRegisterNo())) {
			validateSuccess = false;
			stringBuilder.append("Register no is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(vehicleTO.getEngineNo())) {
			validateSuccess = false;
			stringBuilder.append("Engine no mandatory").append(", ");
		}
		if (StringUtils.isEmpty(vehicleTO.getChessisNo())) {
			validateSuccess = false;
			stringBuilder.append("Chessi number is mandatory").append(", ");
		}
		if (StringUtils.isEmpty(vehicleTO.getFuel())) {
			validateSuccess = false;
			stringBuilder.append("Fuel is mandatory").append(", ");
		}
		if (vehicleTO.getCarModel() == null) {
			validateSuccess = false;
			stringBuilder.append("Car model is mandatory").append(", ");
		}

		if (!validateSuccess) {
			throw new ValidationFailureException(ErrorCodes.VALIDATION_FAILURE.name(), stringBuilder.toString());
		}

	}

}