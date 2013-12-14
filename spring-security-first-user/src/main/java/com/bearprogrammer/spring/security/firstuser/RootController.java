package com.bearprogrammer.spring.security.firstuser;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	
	@RequestMapping("/login")
	public Map<String, Object> login (Map<String, Object> model) {
		return model;
	}
	
	@RequestMapping("/logout")
	public Map<String, Object> logout (Map<String, Object> model) {
		return model;
	}

}
