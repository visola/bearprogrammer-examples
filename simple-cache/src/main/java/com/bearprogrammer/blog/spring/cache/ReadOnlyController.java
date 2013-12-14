package com.bearprogrammer.blog.spring.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReadOnlyController {

	/**
	 * Data should only be read from the cache.
	 */
	@Autowired
	private SimpleCache simpleCache;

	/**
	 * Action that uses data from the cache.
	 */
	@RequestMapping("/example")
	public Map<String, Object> example(String name, Map<String, Object> model) throws Exception {
		System.out.println("Controller: /example");
		model.put(name, simpleCache.getProperty(name));
		return model;
	}
	
}
