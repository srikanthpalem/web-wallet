package org.cap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.model.Customer;
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   private ICustomerService loginservice;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		loginservice=new CustomerServiceImpl();
		String username=request.getParameter("userName");
		String userpwd=request.getParameter("userPwd");
		
		Customer details=new Customer(username,userpwd);
		
		Customer customer2=loginservice.isValidLogin(details);

		if(customer2!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("custId",customer2.getCustomerId() );
			response.sendRedirect("pages/mainPage.html");
		}
		else
		{
			response.sendRedirect("index.html");
		}
			}

}
