package com.bearprogrammer.blog.spring.cache;

import java.util.Map;

/**
 * Example service interface.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
public interface ExampleService {
	
	/**
	 * Loads all data from somewhere.
	 * 
	 * @return A properties object with all data in it.
	 * 
	 * @throws Exception
	 *             Things happen.
	 */
	public Map<String, String> getAll() throws Exception;
	
	/**
	 * Save a new name/value in.
	 * 
	 * @param name
	 *            Name to be created or to be replaced.
	 * @param value
	 *            New value.
	 * @throws Exception
	 *             Things happen.
	 */
	public void save(String name, String value) throws Exception;

}
