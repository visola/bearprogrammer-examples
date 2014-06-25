package com.bearprogrammer.blog.sample.controller;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearprogrammer.blog.sample.ToDo;
import com.bearprogrammer.blog.sample.repository.ToDoRepository;

@Controller
public class ToDoController {
	
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	@Autowired
	ToDoRepository toDoRepository;
	
	boolean isUserInRole(UserDetails user, String role) {
		boolean isInRole = false;
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().equals(role)) {
				isInRole = true;
			}
		}
		logger.debug("User {} has role {}: {}", user.getUsername(), role, isInRole);
		return isInRole;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		return edit(null);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("edit");
		
		if (id == null) {
			mav.addObject("toDo", new ToDo());
		} else {
			mav.addObject("toDo", toDoRepository.findOne(id));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView save(@Valid ToDo toDo, BindingResult validation, @AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		
		if (validation.hasErrors()) {
			mav.setViewName("edit");
		} else {
			toDo.setCreated(Calendar.getInstance());
			toDo.setCreatedBy(currentUser.getUsername());
			toDo.setUpdated(Calendar.getInstance());
			toDo.setUpdatedBy(currentUser.getUsername());
			
			toDoRepository.save(toDo);
			mav.setViewName("redirect:/");
		}
		
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView toDoList(@AuthenticationPrincipal UserDetails currentUser) {
		logger.trace("User requested to do list: {}", currentUser.getUsername());
		
		ModelAndView mav = new ModelAndView("list");
		if (isUserInRole(currentUser, "ROLE_ADMIN")) {
			mav.addObject("toDos", toDoRepository.findAll());
		} else {
			mav.addObject("toDos", toDoRepository.findByAssignedToOrCreatedBy(currentUser.getUsername(),currentUser.getUsername()));
		}
		
		return mav;
	}

}
