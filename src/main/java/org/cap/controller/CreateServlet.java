package org.cap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;
import org.cap.util.AccountType;


@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private Account account;
	
	private ICustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		account=new Account();
		customerService=new CustomerServiceImpl();
		AccountType account_type=AccountType.valueOf(request.getParameter("accountType").toString());
		Double balance=Double.valueOf(request.getParameter("balance"));
		String des=request.getParameter("description");
		account.setAccountType(account_type);
		account.setOpeningBalance(balance);
		account.setDescription(des);
		
		HttpSession session=request.getSession(false);
		Integer custId=Integer.parseInt(session.getAttribute("custId").toString());
		
		//response.getWriter().println(custId);
		Customer customer=customerService.findCustomer(custId);
		account.setCustomer(customer);
		if(customerService.createAccount(account))
		{
			
			response.sendRedirect("pages/createAccount.html");
			
		}
		
	}

}
