package com.bearprogrammer.blog.webmvc.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearprogrammer.blog.webmvc.model.Contact;
import com.bearprogrammer.blog.webmvc.model.ContactDAO;

@WebServlet("/contact/all.html")
public class AllContactsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private ContactDAO contactDAO = new ContactDAO();

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Contact> contacts = contactDAO.findAll();
			
		// Only add if contacts where loaded
		if (contacts.size() > 0) {
			req.setAttribute("contacts", contacts);
		}

		return "/WEB-INF/jsp/contact/all.jsp";
	}
	
}
