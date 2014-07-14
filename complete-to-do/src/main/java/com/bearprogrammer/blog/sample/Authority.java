package com.bearprogrammer.blog.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	Integer id;

	String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

	public Integer getId() {
		return id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
