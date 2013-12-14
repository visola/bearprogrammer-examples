package com.bearprogrammer.blog.spring.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {

	/**
	 * Data should only be read from the cache.
	 */
	@Autowired
	private SimpleCache simpleCache;

	/**
	 * Should only be used to save data.
	 */
	@Autowired
	private ExampleService exampleService;
	
	/**
	 * Action that clears the cache.
	 */
	@RequestMapping("/clear")
	public Map<String, Object> clear(Map<String, Object> model) {
		System.out.println("Controller: /clear");
		simpleCache.clearCache();
		return model;
	}
	
	/**
	 * Add form.
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(Map<String, Object> model) {
		System.out.println("Controller: /add");
		return model;
	}
	
	@RequestMapping("/save")
	public Map<String, Object> save(String name, String value, Map<String, Object> model) throws Exception {
		System.out.println("Controller: /save");
		
		exampleService.save(name, value);
		model.put("name", "value");
		
		// Don't forget to clear the cache
		simpleCache.clearCache();
		return model;
	}

}
