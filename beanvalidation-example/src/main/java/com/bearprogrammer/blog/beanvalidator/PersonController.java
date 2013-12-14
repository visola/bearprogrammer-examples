package com.bearprogrammer.blog.beanvalidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

	/**
	 * Data is going to be stored here.
	 */
	private TreeMap<Integer, Person> people = new TreeMap<Integer, Person>();

	@RequestMapping("/edit")
	public ModelAndView edit(Integer personId) {
		ModelAndView mv = new ModelAndView();

		if (personId != null) {
			Person p = people.get(personId);
			if (p != null) {
				mv.addObject("person", p);
			}
		}

		return mv;
	}

	@RequestMapping("/all")
	public ModelAndView getAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("people", people);
		return mv;
	}

	/**
	 * Setup the date property editor.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/save")
	public ModelAndView save(@Valid Person person, BindingResult bindingResults) {
		ModelAndView mv = new ModelAndView("redirect:all.do");

		// Check for validation errors
		if (bindingResults.hasErrors()) {
			mv.setViewName("edit");

			mv.addObject("person", person);
			
			// Add errors to the Model so that they can be used in the view
			mv.addObject("errors", bindingResults.getFieldErrors());

			// Print the errors to the console
			System.out.println("Validation errors:");
			for (FieldError error : bindingResults.getFieldErrors()) {
				System.out.println(error.getField() + " - " + error.getDefaultMessage());
			}

		} else {
			if (person.getId() == null) {
				person.setId(people.size() + 1);
			} 
			people.put(person.getId(), person);
			
			System.out.println("Person saved!");
		}

		return mv;
	}

}