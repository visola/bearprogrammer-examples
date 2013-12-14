package com.bearprogrammer.user;

public class WrongPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongPasswordException(String message) {
		super(message);
	}

}
