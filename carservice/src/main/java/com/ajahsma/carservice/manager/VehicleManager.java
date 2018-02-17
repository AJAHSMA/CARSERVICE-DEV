package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.VehicleDTO;
import com.ajahsma.carservice.model.VehicleTO;

/**
 * @author SHARAN A
 */

public interface VehicleManager extends DefaultManager {

	public VehicleTO convertVehicleDTOToVehocleTO(VehicleDTO vehicleDTO) throws InstantiationException, IllegalAccessException;
	
}
