package com.bearprogrammer.spring.security.firstuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bearprogrammer.user.UserRepository;

public class CreateFirstUserFilter implements Filter {
	
	private static final Authentication FIRST_USER_AUTHENTICATION = new FirstUserAuthentication();
	
	private static final String FIRST_USER_CREATE_URL = "/firstUser/create.do";
	private static final String FIRST_USER_SAVE_URL = "/firstUser/save.do";
	
	private long userCount = -1;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void destroy() {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		// If no user in the database, add first user authentication
		if (userCount == 0) {
			SecurityContextHolder.getContext().setAuthentication(FIRST_USER_AUTHENTICATION);
		
			// If going into first user create or save page, let it pass 
			if (httpRequest.getRequestURL().toString().endsWith(FIRST_USER_CREATE_URL) ||
					httpRequest.getRequestURL().toString().endsWith(FIRST_USER_SAVE_URL)) {
				chain.doFilter(request, response);
				
				// If just saved first user, update user count
				if (httpRequest.getRequestURL().toString().endsWith(FIRST_USER_SAVE_URL)) {
					updateUserCount();
					
					// Clear security context
					SecurityContextHolder.getContext().setAuthentication(null);
				}
				
			// Otherwise, redirect to first user create page
			} else {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect(httpRequest.getContextPath() + FIRST_USER_CREATE_URL);
			}
			
		} else { // If there's already a user in the database
			chain.doFilter(request, response);
		}
	}
	
	public void start() {
		// On initialization, get user count
		updateUserCount();
	}

	private void updateUserCount() {
		userCount = userRepository.count();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}

}
