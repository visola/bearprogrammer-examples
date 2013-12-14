package com.bearprogrammer.blog.webmvc.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearprogrammer.blog.webmvc.model.Contact;
import com.bearprogrammer.blog.webmvc.model.ContactDAO;

@WebServlet("/contact/edit.html")
public class EditContactServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private ContactDAO contactDAO = new ContactDAO();
	
	protected String execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Contact contact = null;
		
		// Get contact id
		String sID = request.getParameter("id");
		
		// Try to load it from the database
		if (sID != null) {
			Integer id = Integer.parseInt(sID);
			contact = contactDAO.findOne(id);
			
			request.setAttribute("contact", contact);
			
		}
		
		return "/WEB-INF/jsp/contact/edit.jsp";
	}

}
