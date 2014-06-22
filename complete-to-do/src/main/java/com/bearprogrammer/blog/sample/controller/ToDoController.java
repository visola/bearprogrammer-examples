package com.bearprogrammer.blog.sample.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearprogrammer.blog.sample.ToDo;
import com.bearprogrammer.blog.sample.repository.ToDoRepository;

@Controller
@RequestMapping("/todo")
public class ToDoController {
	
	@Autowired
	ToDoRepository toDoRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView toDoList(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		
		if (isUserInRole(currentUser.getAuthorities(), "ROLE_ADMIN")) {
			mav.addObject("toDos", toDoRepository.findAll());
		} else {
			mav.addObject("toDos", toDoRepository.findByAssignedTo(currentUser.getUsername()));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView save(ToDo toDo) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}

	boolean isUserInRole(Collection<? extends GrantedAuthority> authorities, String role) {
		boolean isInRole = false;
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().equals(role)) {
				isInRole = true;
			}
		}
		return isInRole;
	}

}
