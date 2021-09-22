package org.cap.controller;

import java.io.IOException;
import java.time.LocalDate;
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


@WebServlet("/PrintTransactionLogicServlet")
public class PrintTransactionLogicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ICustomerService customerService=new CustomerServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long accountNo=Long.parseLong(request.getParameter("accountno"));
		Account account=customerService.findAccount(accountNo);
		String toDate1= request.getParameter("to date");
		String fromDate1= request.getParameter("from date");
		String[] dob=toDate1.split("-");
		LocalDate toDate=(LocalDate.of(Integer.parseInt(dob[0]),
				Integer.parseInt(dob[1]), Integer.parseInt(dob[2])));
		dob=fromDate1.split("-");
		LocalDate fromDate=(LocalDate.of(Integer.parseInt(dob[0]),
				Integer.parseInt(dob[1]), Integer.parseInt(dob[2])));
		List<Transaction> transactions=customerService.getTransactionsFromTo(account,fromDate,toDate);
		HttpSession session=request.getSession(false);
		session.setAttribute("transactions", transactions);
		session.setAttribute("acc", accountNo);
		session.setAttribute("r", 1);
		response.sendRedirect("PrintTransactionServlet");
	}

}
