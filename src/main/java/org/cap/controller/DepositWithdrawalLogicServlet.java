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
import org.cap.util.TransactionType;


@WebServlet("/DepositWithdrawalLogicServlet")
public class DepositWithdrawalLogicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ICustomerService customerService=new CustomerServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Transaction transaction=new Transaction();
		String type=request.getParameter("type");
		long accountno=Long.parseLong(request.getParameter("accountno"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String desc=request.getParameter("desc");
		
		Account account1=new Account();
		account1=customerService.findAccount(accountno);
		
		
		
		transaction.setAmount(amount);
		if(type.equals("withdraw")) {
			transaction.setTransactionType(TransactionType.DEBIT);
			transaction.setFromAccount(account1);
		}
		else if(type.equals("deposit"))
		{
			transaction.setTransactionType(TransactionType.CREDIT);
			transaction.setToAccount(account1);
		}
		transaction.setDescription(desc);
		
		transaction.setTransactionDate(new Date());
		
		boolean flag=customerService.createTransaction(transaction);
		if(flag==true)
			response.sendRedirect("DepositWithdrawal");
		
		
			
	}

}
