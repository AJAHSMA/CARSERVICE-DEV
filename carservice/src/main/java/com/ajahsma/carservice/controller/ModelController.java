package com.ajahsma.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.ModelManager;

/**
 * @author SHARAN A
 */

@Controller("modelController")
@RequestMapping(value = "/model")
public class ModelController extends AbstractController {

	@Autowired
	private ModelManager modelManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.modelManager;
	}
}