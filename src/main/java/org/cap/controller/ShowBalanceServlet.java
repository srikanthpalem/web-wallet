package org.cap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.model.Account;
import org.cap.model.Transaction;
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;

@WebServlet("/ShowBalanceServlet")
public class ShowBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ICustomerService customerService=new CustomerServiceImpl();
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long accountNo=Long.parseLong(request.getParameter("accountno"));
		Account account=customerService.findAccount(accountNo);
		double finalbalance=customerService.getTotal(account);
		HttpSession session=request.getSession(false);
		session.setAttribute("finalbalance", finalbalance);
		session.setAttribute("accno" , accountNo);
		session.setAttribute("r", 1);
		response.sendRedirect("ShowBalance");
	}

}
