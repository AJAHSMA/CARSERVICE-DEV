package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.VehicleCustomerRegistrationDTO;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;

/**
 * @author SHARAN A
 */

public interface VehicleCustomerRegistrationManager extends DefaultManager {

	VehicleCustomerRegistrationTO convertVehicleDTOToVehocleTO(VehicleCustomerRegistrationDTO vehicleCustomerRegistrationDto) throws InstantiationException, IllegalAccessException;

}
