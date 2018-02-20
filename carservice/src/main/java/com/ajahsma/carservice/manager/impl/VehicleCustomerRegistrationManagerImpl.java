package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ajahsma.carservice.dao.VehicleCustomerRegistrationDao;
import com.ajahsma.carservice.dto.CheckNomenclatureDTO;
import com.ajahsma.carservice.dto.CustomerDemandedRepairDTO;
import com.ajahsma.carservice.dto.ItemDTO;
import com.ajahsma.carservice.dto.PrimaryApprovelAndEstimationDTO;
import com.ajahsma.carservice.dto.VehicleCustomerRegistrationDTO;
import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.CheckNomenclatureTO;
import com.ajahsma.carservice.model.CustomerDemandedRepairTO;
import com.ajahsma.carservice.model.CustomerTO;
import com.ajahsma.carservice.model.InventoryTO;
import com.ajahsma.carservice.model.ItemTO;
import com.ajahsma.carservice.model.NomenclatureTO;
import com.ajahsma.carservice.model.PrimaryApprovelAndEstimationTO;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;
import com.ajahsma.carservice.model.VehicleTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class VehicleCustomerRegistrationManagerImpl extends DefaultManagerImpl implements VehicleCustomerRegistrationManager {

	@Autowired
	private VehicleManager vehicleManager;
	
	@Autowired
	private CustomerManager customerManager;
	
	@Autowired
	public void setDefaultDao(VehicleCustomerRegistrationDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private VehicleCustomerRegistrationDao getVehicleCustomerRegistrationDao() {
		return (VehicleCustomerRegistrationDao) getDefaultDao();
	}

	@Override
	public VehicleCustomerRegistrationTO convertVehicleDTOToVehocleTO(VehicleCustomerRegistrationDTO vehicleCustomerRegistrationDto) throws InstantiationException, IllegalAccessException  {

		VehicleCustomerRegistrationTO vehicleCustomerRegistrationTO = CarServiceUtils.copyBeanProperties(vehicleCustomerRegistrationDto, VehicleCustomerRegistrationTO.class);

		VehicleTO vehicleTO = null;
		CustomerTO customerTO = null;
		InventoryTO inventoryTO = null;

		if(vehicleCustomerRegistrationDto.getVehicle() != null) {
			if(vehicleCustomerRegistrationDto.getVehicle().getId() != null) {
				vehicleTO = (VehicleTO) vehicleManager.getDomain(VehicleTO.class, vehicleCustomerRegistrationDto.getVehicle().getId());
			}
			else {
				vehicleTO = vehicleManager.convertVehicleDTOToVehicleTO(vehicleCustomerRegistrationDto.getVehicle());
			}
		}
		if(vehicleCustomerRegistrationDto.getCustomer() != null) {
			if(vehicleCustomerRegistrationDto.getCustomer().getId() != null) {
				customerTO = (CustomerTO) customerManager.getDomain(CustomerTO.class, vehicleCustomerRegistrationDto.getCustomer().getId());
			}
			else {
				customerTO = customerManager.convertCustomerDTOToCustomerTO(vehicleCustomerRegistrationDto.getCustomer());
			}
		}
		if(vehicleCustomerRegistrationDto.getInventory() != null) {
			inventoryTO = CarServiceUtils.copyBeanProperties(vehicleCustomerRegistrationDto.getInventory(), InventoryTO.class);

			// Copy Items
			if(CarServiceUtils.isNotEmpty(vehicleCustomerRegistrationDto.getInventory().getItems())) {
				for (ItemDTO itemDto : vehicleCustomerRegistrationDto.getInventory().getItems()) {
					ItemTO itemTO = CarServiceUtils.copyBeanProperties(itemDto, ItemTO.class);
					
					inventoryTO.addItem(itemTO);
				}
			}
			// Copy PrimaryApprovelAndEstimations
			if(CarServiceUtils.isNotEmpty(vehicleCustomerRegistrationDto.getInventory().getPrimaryApprovelAndEstimations())) {
				for (PrimaryApprovelAndEstimationDTO primaryApprovelAndEstimationDTO : vehicleCustomerRegistrationDto.getInventory().getPrimaryApprovelAndEstimations()) {
					PrimaryApprovelAndEstimationTO primaryApprovelAndEstimationDto = CarServiceUtils.copyBeanProperties(primaryApprovelAndEstimationDTO, PrimaryApprovelAndEstimationTO.class);
					
					inventoryTO.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimationDto);
				}
			}
			// Copy CheckNomenclatures
			if(CarServiceUtils.isNotEmpty(vehicleCustomerRegistrationDto.getInventory().getCheckNomenclatures())) {
				for (CheckNomenclatureDTO checkNomenclatureDto : vehicleCustomerRegistrationDto.getInventory().getCheckNomenclatures()) {
					CheckNomenclatureTO checkNomenclatureTO = CarServiceUtils.copyBeanProperties(checkNomenclatureDto, CheckNomenclatureTO.class);
					
					NomenclatureTO nomenclatureTO = null;
					if(checkNomenclatureDto.getNomenclature() != null) {
						nomenclatureTO = CarServiceUtils.copyBeanProperties(checkNomenclatureDto.getNomenclature(), NomenclatureTO.class);
					}
					checkNomenclatureTO.setNomenclature(nomenclatureTO);
					
					inventoryTO.addCheckNomenclature(checkNomenclatureTO);
				}
			}
		}
		vehicleCustomerRegistrationTO.setVehicle(vehicleTO);
		vehicleCustomerRegistrationTO.setCustomer(customerTO);
		vehicleCustomerRegistrationTO.setInventory(inventoryTO);
		
		if(!CollectionUtils.isEmpty(vehicleCustomerRegistrationDto.getCustomerDemandedRepairs())) {
			for (CustomerDemandedRepairDTO customerDemandedRepairDTO : vehicleCustomerRegistrationDto.getCustomerDemandedRepairs()) {
				CustomerDemandedRepairTO customerDemandedRepairTO = CarServiceUtils.copyBeanProperties(customerDemandedRepairDTO, CustomerDemandedRepairTO.class);

				vehicleCustomerRegistrationTO.addCustomerDemandedRepair(customerDemandedRepairTO);
				
			}
			
		}
		
		return vehicleCustomerRegistrationTO;
	}

	
}
