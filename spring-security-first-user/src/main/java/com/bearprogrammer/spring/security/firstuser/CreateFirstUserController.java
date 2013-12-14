package com.bearprogrammer.spring.security.firstuser;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bearprogrammer.user.User;
import com.bearprogrammer.user.UserRepository;

@Controller
@RequestMapping("/firstUser")
public class CreateFirstUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/create")
	public Map<String, Object> createFirstUser(Map<String, Object> model) {
		return model;
	}
	
	@RequestMapping("/save")
	public String saveFirstUser(User user, Map<String, Object> model) {
		model.put("user", userRepository.save(user));
		return "redirect:/login.do";
	}

}
