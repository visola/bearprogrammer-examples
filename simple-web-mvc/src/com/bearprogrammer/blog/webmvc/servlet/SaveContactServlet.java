package com.bearprogrammer.blog.webmvc.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bearprogrammer.blog.webmvc.model.Contact;
import com.bearprogrammer.blog.webmvc.model.ContactDAO;

@WebServlet("/contact/save.html")
public class SaveContactServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private ContactDAO contactDAO = new ContactDAO();
	
	protected String execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Contact contact = null;
		
		// Get contact id
		String sID = request.getParameter("id");
		
		// Try to load it from the database
		if (sID != null && !sID.trim().equals("")) {
			Integer id = Integer.parseInt(sID);
			contact = contactDAO.findOne(id);
			
		} else { // No ID provided, create a new contact
			contact = new Contact();
		}
		
		// Set new data to contact
		contact.setName(request.getParameter("name"));
		contact.setEmail(request.getParameter("email"));
		
		// Save it
		contactDAO.save(contact);
		
		/* Uses the post-redirect pattern to avoid duplicate:
		 * http://en.wikipedia.org/wiki/Post/Redirect/Get
		 */
		return "redirect:/contact/all.html";
	}

}
