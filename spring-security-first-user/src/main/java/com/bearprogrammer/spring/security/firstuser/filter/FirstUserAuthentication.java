package com.bearprogrammer.spring.security.firstuser.filter;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class FirstUserAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FIRST_USER_AUTHORITY = "firstuser";

	private static final GrantedAuthority AUTHORITY = new GrantedAuthority(){
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthority() {
			return FIRST_USER_AUTHORITY;
		}
	};
	
	private static final Collection<? extends GrantedAuthority> FIRST_USER_AUTHORITIES = Arrays.asList(AUTHORITY);
	private static final UsernamePasswordAuthenticationToken CREDENTIALS = 
			new UsernamePasswordAuthenticationToken("First User", "No Password", FIRST_USER_AUTHORITIES);

	@Override
	public String getName() {
		return "First User";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return FIRST_USER_AUTHORITIES;
	}

	@Override
	public Object getCredentials() {
		return CREDENTIALS;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return CREDENTIALS.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		// Do nothing
	}

}
