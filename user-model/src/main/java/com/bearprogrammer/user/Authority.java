package com.bearprogrammer.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String authority;
	
	public Authority() {
		super();
	}
	
	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
