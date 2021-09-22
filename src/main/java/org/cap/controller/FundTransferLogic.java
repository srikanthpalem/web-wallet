package org.cap.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.model.Account;
import org.cap.model.Transaction;
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;

@WebServlet("/FundTransferLogic")
public class FundTransferLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private static ICustomerService customerService=new CustomerServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Transaction transaction=new Transaction();
		String type=request.getParameter("type");
		long fromaccountno=Long.parseLong(request.getParameter("from account"));
		long toaccountno=Long.parseLong(request.getParameter("to account"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String desc=request.getParameter("desc");
		
		Account account1=new Account();
		account1=customerService.findAccount(fromaccountno);
		
		Account account2=new Account();
		account2=customerService.findAccount(toaccountno);
		transaction.setAmount(amount);
			transaction.setFromAccount(account1);
			transaction.setToAccount(account2);
	
		transaction.setDescription(desc);
		
		transaction.setTransactionDate(new Date());
		
		boolean flag=customerService.createTransaction(transaction);
		if(flag==true)
			response.sendRedirect("FundTransferServlet");
		
		
			
	}

}
