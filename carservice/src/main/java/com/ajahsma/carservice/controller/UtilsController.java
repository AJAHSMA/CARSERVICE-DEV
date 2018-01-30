package com.ajahsma.carservice.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.manager.ApplicationUserManager;
import com.ajahsma.carservice.manager.CarModelManager;
import com.ajahsma.carservice.manager.CityManager;
import com.ajahsma.carservice.manager.CustomerManager;
import com.ajahsma.carservice.manager.DesignationManager;
import com.ajahsma.carservice.manager.EmployeeManager;
import com.ajahsma.carservice.manager.ItemManager;
import com.ajahsma.carservice.manager.NomenclatureManager;
import com.ajahsma.carservice.manager.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.manager.VehicleManager;
import com.ajahsma.carservice.model.CheckNomenclatureTO;
import com.ajahsma.carservice.model.CustomerTO;
import com.ajahsma.carservice.model.InventoryTO;
import com.ajahsma.carservice.model.ItemTO;
import com.ajahsma.carservice.model.NomenclatureTO;
import com.ajahsma.carservice.model.PrimaryApprovelAndEstimationTO;
import com.ajahsma.carservice.model.VehicleCustomerRegistrationTO;
import com.ajahsma.carservice.model.VehicleTO;
import com.ajahsma.carservice.utils.CarServiceUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author SHARAN A
 */

@Controller
public class UtilsController extends AbstractController {

	@Autowired
	ItemManager itemManager;

	@Autowired
	NomenclatureManager nomenclatureManager;

	@Autowired
	VehicleManager vehicleManager;

	@Autowired
	CustomerManager customerManager;

	@Autowired
	EmployeeManager employeeManager;

	@Autowired
	ApplicationUserManager applicationUserManager;

	@Autowired
	CarModelManager modelUserManager;

	@Autowired
	CityManager cityManager;

	@Autowired
	VehicleCustomerRegistrationManager vehicleCustomerRegistrationManager;

	@Autowired
	DesignationManager designationManager;

	@RequestMapping(value = "/objectToJson")
	@ResponseBody
	public String getObjectToJson() throws Exception {

		// -------------------------------------------------

		CustomerTO customer = (CustomerTO) customerManager.getDomain(CustomerTO.class, new Long(1));
		CustomerTO customer1 = (CustomerTO) customerManager.getDomain(CustomerTO.class, new Long(2));
		VehicleTO vehicle = (VehicleTO) vehicleManager.getDomain(VehicleTO.class, new Long(1));
		VehicleTO vehicle1 = (VehicleTO) vehicleManager.getDomain(VehicleTO.class, new Long(2));
		ItemTO item = (ItemTO) itemManager.getDomain(ItemTO.class, new Long(1));
		ItemTO item1 = (ItemTO) itemManager.getDomain(ItemTO.class, new Long(2));
		ItemTO item2 = (ItemTO) itemManager.getDomain(ItemTO.class, new Long(3));
		ItemTO item3 = (ItemTO) itemManager.getDomain(ItemTO.class, new Long(4));

		NomenclatureTO nomenclature = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(1));
		NomenclatureTO nomenclature1 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(2));
		NomenclatureTO nomenclature2 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(3));
		NomenclatureTO nomenclature3 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(4));
		NomenclatureTO nomenclature4 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(5));
		NomenclatureTO nomenclature5 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(6));
		NomenclatureTO nomenclature6 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(7));
		NomenclatureTO nomenclature7 = (NomenclatureTO) nomenclatureManager.getDomain(NomenclatureTO.class, new Long(8));

		PrimaryApprovelAndEstimationTO primaryApprovelAndEstimation = new PrimaryApprovelAndEstimationTO("desc1", new BigDecimal("1000"), new BigDecimal("200"), new BigDecimal("1200"), null);
		PrimaryApprovelAndEstimationTO primaryApprovelAndEstimation1 = new PrimaryApprovelAndEstimationTO("desc2", new BigDecimal("1200"), new BigDecimal("200"), new BigDecimal("1400"), null);
		PrimaryApprovelAndEstimationTO primaryApprovelAndEstimation2 = new PrimaryApprovelAndEstimationTO("desc3", new BigDecimal("1400"), new BigDecimal("200"), new BigDecimal("1600"), null);

		InventoryTO inventory = new InventoryTO(new BigDecimal(1200), "30", "CEATE", "0001", "CE", "1001");

		inventory.addItem(item);
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);

		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation);
		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation1);
		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation2);

		CheckNomenclatureTO checkNomenclature = new CheckNomenclatureTO(nomenclature, true, "Remarks 1");
		CheckNomenclatureTO checkNomenclature1 = new CheckNomenclatureTO(nomenclature1, true, "Remarks 2");
		CheckNomenclatureTO checkNomenclature2 = new CheckNomenclatureTO(nomenclature4, true, "Remarks 3");
		CheckNomenclatureTO checkNomenclature3 = new CheckNomenclatureTO(nomenclature7, true, "Remarks 4");
		CheckNomenclatureTO checkNomenclature4 = new CheckNomenclatureTO(nomenclature3, true, "Remarks 5");
		CheckNomenclatureTO checkNomenclature5 = new CheckNomenclatureTO(nomenclature6, true, "Remarks 6");

		inventory.addCheckNomenclature(checkNomenclature);
		inventory.addCheckNomenclature(checkNomenclature1);
		inventory.addCheckNomenclature(checkNomenclature2);
		inventory.addCheckNomenclature(checkNomenclature3);
		inventory.addCheckNomenclature(checkNomenclature4);
		inventory.addCheckNomenclature(checkNomenclature5);

		VehicleCustomerRegistrationTO vCR = new VehicleCustomerRegistrationTO(new Long(1111), "0001", Calendar.getInstance(), Calendar.getInstance(), vehicle, customer, inventory);

//		vehicleCustomerRegistrationManager.saveDomain(vCR);

		String jsonString = "";

		try {
			jsonString = CarServiceUtils.convertObjectToJson(vCR);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}