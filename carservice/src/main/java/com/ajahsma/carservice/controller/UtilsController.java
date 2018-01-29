package com.ajahsma.carservice.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajahsma.carservice.model.CheckNomenclature;
import com.ajahsma.carservice.model.Customer;
import com.ajahsma.carservice.model.Inventory;
import com.ajahsma.carservice.model.Item;
import com.ajahsma.carservice.model.Nomenclature;
import com.ajahsma.carservice.model.PrimaryApprovelAndEstimation;
import com.ajahsma.carservice.model.Vehicle;
import com.ajahsma.carservice.model.VehicleCustomerRegistration;
import com.ajahsma.carservice.service.ApplicationUserManager;
import com.ajahsma.carservice.service.CityManager;
import com.ajahsma.carservice.service.CustomerManager;
import com.ajahsma.carservice.service.DesignationManager;
import com.ajahsma.carservice.service.EmployeeManager;
import com.ajahsma.carservice.service.ItemManager;
import com.ajahsma.carservice.service.ModelManager;
import com.ajahsma.carservice.service.NomenclatureManager;
import com.ajahsma.carservice.service.VehicleCustomerRegistrationManager;
import com.ajahsma.carservice.service.VehicleManager;

/**
 * @author SHARAN A
 */

@Controller("utilsController")
@RequestMapping(value = "/utils")
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
	ModelManager modelUserManager;

	@Autowired
	CityManager cityManager;

	@Autowired
	VehicleCustomerRegistrationManager vehicleCustomerRegistrationManager;

	@Autowired
	DesignationManager designationManager;

	@RequestMapping(value = "/jsonToObject")
	@ResponseBody
	public String getJsonToObject() throws Exception {

		// -------------------------------------------------

		Customer customer = (Customer) customerManager.getDomain(Customer.class, new Long(1));
		Customer customer1 = (Customer) customerManager.getDomain(Customer.class, new Long(2));
		Vehicle vehicle = (Vehicle) vehicleManager.getDomain(Vehicle.class, new Long(1));
		Vehicle vehicle1 = (Vehicle) vehicleManager.getDomain(Vehicle.class, new Long(2));
		Item item = (Item) itemManager.getDomain(Item.class, new Long(1));
		Item item1 = (Item) itemManager.getDomain(Item.class, new Long(2));
		Item item2 = (Item) itemManager.getDomain(Item.class, new Long(3));
		Item item3 = (Item) itemManager.getDomain(Item.class, new Long(4));
		
		Nomenclature nomenclature = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(1));
		Nomenclature nomenclature1 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(2));
		Nomenclature nomenclature2 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(3));
		Nomenclature nomenclature3 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(4));
		Nomenclature nomenclature4 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(5));
		Nomenclature nomenclature5 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(6));
		Nomenclature nomenclature6 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(7));
		Nomenclature nomenclature7 = (Nomenclature) nomenclatureManager.getDomain(Nomenclature.class, new Long(8));
		
		/*Set<Item> items = new HashSet<Item>();
		items.add(item);
		items.add(item1);
		items.add(item2);
		items.add(item3);*/
		
//		PrimaryApprovelAndEstimation(String description, BigDecimal material, BigDecimal labour, BigDecimal total, Inventory inventory) {
		PrimaryApprovelAndEstimation primaryApprovelAndEstimation = new PrimaryApprovelAndEstimation("desc1", new BigDecimal("1000"), new BigDecimal("200"), new BigDecimal("1200"), null); 
		PrimaryApprovelAndEstimation primaryApprovelAndEstimation1 = new PrimaryApprovelAndEstimation("desc2", new BigDecimal("1200"), new BigDecimal("200"), new BigDecimal("1400"), null); 
		PrimaryApprovelAndEstimation primaryApprovelAndEstimation2 = new PrimaryApprovelAndEstimation("desc3", new BigDecimal("1400"), new BigDecimal("200"), new BigDecimal("1600"), null);
		
		
		Inventory inventory = new Inventory(new BigDecimal(1200), "30", "CEATE", "0001", "CE", "1001");
		
		inventory.addItem(item);
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		
		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation);
		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation1);
		inventory.addPrimaryApprovelAndEstimation(primaryApprovelAndEstimation2);
		// VehicleCustomerRegistration(Long distanceReading, String jobCardNo, Calendar deliveryDateTime, Calendar currentDatetime, Attachment customerSignature, Vehicle vehicle, Customer customer, Inventory inventory)

		
		CheckNomenclature checkNomenclature = new CheckNomenclature(nomenclature, true, "Remarks 1");
		CheckNomenclature checkNomenclature1 = new CheckNomenclature(nomenclature1, true, "Remarks 2");
		CheckNomenclature checkNomenclature2 = new CheckNomenclature(nomenclature4, true, "Remarks 3");
		CheckNomenclature checkNomenclature3 = new CheckNomenclature(nomenclature7, true, "Remarks 4");
		CheckNomenclature checkNomenclature4 = new CheckNomenclature(nomenclature3, true, "Remarks 5");
		CheckNomenclature checkNomenclature5 = new CheckNomenclature(nomenclature6, true, "Remarks 6");
		
		inventory.addCheckNomenclature(checkNomenclature);
		inventory.addCheckNomenclature(checkNomenclature1);
		inventory.addCheckNomenclature(checkNomenclature2);
		inventory.addCheckNomenclature(checkNomenclature3);
		inventory.addCheckNomenclature(checkNomenclature4);
		inventory.addCheckNomenclature(checkNomenclature5);
		
//		nomenclatureManager
		
		VehicleCustomerRegistration vCR = new VehicleCustomerRegistration(new Long(1111), "0001", Calendar.getInstance(), Calendar.getInstance(), vehicle, customer, inventory);
		
		vehicleCustomerRegistrationManager.saveDomain(vCR);
		
		
		nomenclatureManager.deleteDomain(nomenclature5);
		
		// ----------------------------------------------------
		String jsonString = "";

		/*
		 * try { jsonString = CarServiceUtils.convertObjectToJson(state);
		 * System.out.println(jsonString); } catch (JsonProcessingException e) {
		 * e.printStackTrace(); }
		 */

		return jsonString;
	}

}