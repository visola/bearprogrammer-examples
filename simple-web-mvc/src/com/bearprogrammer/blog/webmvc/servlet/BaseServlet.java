package com.bearprogrammer.blog.webmvc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PREFIX_REDIRECT = "redirect:";

	protected abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectTo = null;
		
		try {
			redirectTo = execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			redirectTo = "/WEB-INF/jsp/error.jsp";
		}
		
		dispatch(request, response, redirectTo);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String redirectTo) throws IOException, ServletException {
		// Check if it's a redirect instruction
		if (redirectTo.startsWith(PREFIX_REDIRECT)) {
			redirectTo = redirectTo.substring(PREFIX_REDIRECT.length(), redirectTo.length());
			
			if (redirectTo.startsWith("/")) {
				redirectTo = request.getContextPath() + redirectTo;
			}
			
			response.sendRedirect(redirectTo);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirectTo);
			dispatcher.forward(request, response);
		}
	}
	
}
