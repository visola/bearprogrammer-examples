package com.bearprogrammer.blog.webmvc.model;

/**
 * Exceptions that happen in the data layer should be encapsulated in this
 * exception.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
public class DataAccessException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

}
