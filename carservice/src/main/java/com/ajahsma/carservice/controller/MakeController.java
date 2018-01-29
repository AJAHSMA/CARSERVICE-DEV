package com.ajahsma.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajahsma.carservice.service.DefaultManager;
import com.ajahsma.carservice.service.MakeManager;

/**
 * @author SHARAN A
 */

@Controller("makeController")
@RequestMapping(value = "/make")
public class MakeController extends AbstractController {

	@Autowired
	private MakeManager makeManager;

	@Override
	protected DefaultManager getDefaultManager() {
		return this.makeManager;
	}
}