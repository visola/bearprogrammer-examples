package com.bearprogrammer.blog.spring.cache;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimerFilter implements Filter {

	public void destroy() {
		// Do nothing
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long start = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		System.out.println((System.currentTimeMillis() - start) + " ms to process " + ( (HttpServletRequest)request ).getRequestURL());
		System.out.println();
	}

	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}

}
