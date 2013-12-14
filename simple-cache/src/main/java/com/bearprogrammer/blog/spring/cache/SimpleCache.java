package com.bearprogrammer.blog.spring.cache;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class represents the application cache. This is
 * where all data will be stored, once per application.
 * 
 * Everything needs to be synchronized otherwise, it might happen
 * that one user is clearing the cache and another one will get
 * it empty.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
public class SimpleCache {
	
	/**
	 * This is the service that the cache will use to get to the
	 * data.
	 */
	@Autowired
	private ExampleService service;
	
	/**
	 * This is the cache data.
	 */
	private Map<String, String> cache = null;
	
	/**
	 * Make sure that the data is loaded in the cache and then return it from it. 
	 * 
	 * @param name Name of the property.
	 * @return Value of the property.
	 * @throws IOException Se der algum problema na hora de carregar os dados do arquivo.
	 */
	public synchronized String getProperty(String name) throws Exception  {
		System.out.println("Cache: Reading property " + name + "...");
		loadData();
		return cache.get(name);
	}
	
	/**
	 * Check if the cache is loaded, if not, load all data and store it. If it is already
	 * filled out, then just return.
	 * 
	 * @throws Exception If any problem happens while loading data.
	 */
	public synchronized void loadData() throws Exception {
		System.out.println("Cache: Checking if data is loaded...");
		if (cache == null) {
			System.out.println("Cache: Loading data...");
			cache = service.getAll();
			System.out.println("Cache: Data loaded.");
		} else {
			System.out.println("Cache: Don't need to reload data.");
		}
	}

	/**
	 * Clear cached data. This data needs to be called everytime a new entry is saved
	 * in the database. This way, the cache will always have the latest version of the
	 * data.
	 */
	public synchronized void clearCache() {
		System.out.println("Cache: Clearing cache data...");
		cache = null;
		System.out.println("Cache: Cache data cleared.");
	}

}