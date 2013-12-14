package com.bearprogrammer.blog.modelattribute.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bearprogrammer.blog.modelattribute.model.Person;
import com.bearprogrammer.blog.modelattribute.model.PersonRepository;


@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping("/all")
	public Model all(Model model) {
		System.out.println(">> all");
		model.addAttribute("personList", personRepository.findAll());
		return model;
	}

	@RequestMapping("/edit")
	public Person edit(Person person) {
		System.out.println(">> edit");
		return person;
	}

	@ModelAttribute
	public Model getPerson(Integer personId, Model model) {
		System.out.println(">> getPerson");
		
		if (personId != null) {
			model.addAttribute("person", personRepository.findOne(personId));
			System.out.println(">> Person loaded");
		}
		
		return model;
	}
	
	public PersonRepository getPersonRepository() {
		return personRepository;
	}
	
	@RequestMapping("/save")
	public String save(Person person) {
		System.out.println(">> save");
		
		System.out.println("\t>> created is: " + person.getCreated());
		if (person.getCreated() == null) {
			person.setCreated(Calendar.getInstance());
		}
		
		personRepository.save(person);
		return "redirect:all.do";
	}
	
	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

}