package com.onlinetutorialspoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetutorialspoint.dao.PersonDAO;
import com.onlinetutorialspoint.model.Person;

@Controller("personController")
@RequestMapping(value = "/person")
public class PersonController {
	@Autowired
	private PersonDAO personDao;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			Person person = new Person();
			person.setId(id);
			personDao.delete(person);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Person succesfully deleted!";
	}

	@RequestMapping(value = "/save",method=RequestMethod.POST,produces= "application/json")
	@ResponseBody
	public String create(@RequestBody Person person) {
		try {
			/*Person person = new Person();
			person.setName(person);
			person.setCity(city);*/
			personDao.savePerson(person);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Person succesfully saved!";
	}
	
	@RequestMapping(value = "/allPersons")
	@ResponseBody
	public List<Person> getAllPersons() {
		try {
			return personDao.getAllPersons();
		} catch (Exception ex) {
			return null;
		}
	}
}
