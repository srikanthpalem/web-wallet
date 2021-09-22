package org.cap.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/PrintTransactionServlet")
public class PrintTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ICustomerService service=new CustomerServiceImpl();    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Integer custId=(Integer)session.getAttribute("custId");
		List<Account> accounts=service.getAccounts(custId);
		if(session.getAttribute("transactions")==null)
		{
			session.setAttribute("transactions",0);
		}
		if(session.getAttribute("r")==null)
			session.setAttribute("r", 0);
		if(session.getAttribute("acc")==null)
			session.setAttribute("acc", 0);
			PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" +
				"<link href='css/mainPage.css' rel='stylesheet' type='text/css'>"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<pre><b>                         print transcations</b></pre>\r\n" + 
				"<form method=\"post\" action='PrintTransactionLogicServlet'>\r\n" + 
				"		<p>choose account\r\n" + 
				"		<select name='accountno' id='dynamicaccount'>\r\n" );
		Long acc=Long.parseLong((session.getAttribute("acc").toString()));
		for(Account account:accounts){
			if(acc==account.getAccountNo()&&(Integer)session.getAttribute("r")==1)
			out.println("<option value='"+account.getAccountNo()+"' selected>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
			else
				out.println("<option value='"+account.getAccountNo()+"'>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
		}			 
				out.println("</select></p>\r\n" + 
						"<pre>from date:  <input type=\"date\" name=\"from date\" size=\"20\" required >  to date:  <input type=\"date\" name=\"to date\" size=\"20\" required ><br>  \r\n" + 
				"                               <input type=\"submit\" name=\"print\" value=\"print\">\r\n" + 
				"</pre>\r\n" + 
				"<table border=\"1\" style=\"padding: 10px;\">\r\n" + 
				"<tr>\r\n" + 
				"<th>from account</th>\r\n" + 
				"<th>to account</th>\r\n" + 
				"<th>Transaction Date</th>\r\n" + 
				"<th>debit/credit</th>\r\n" + 
				"<th>amount</th>\r\n" + 
				"</tr>\r\n" ); 
				List<Transaction> transactions=null;
				if(session.getAttribute("transactions")!=null&&(Integer)session.getAttribute("r")!=0)
				 {transactions= (List<Transaction>) session.getAttribute("transactions");
				for(Transaction transaction:transactions) { 
											 
					 							out.println("<tr>"); 
					 							
					 							
					 							/*out.println("<td>"+transaction.getDescription()+"</td>"); 
					 							out.println("<td>"+transaction.getTransactionId()+"</td>"); */
					 							
					 							if(transaction.getFromAccount()!=null)
					 							out.println("<td>"+transaction.getFromAccount().getAccountNo()+"</td>"); 
					 							else
					 								out.println("<td></td>");
					 							if(transaction.getToAccount()!=null)
					 							out.println("<td>"+transaction.getToAccount().getAccountNo()+"</td>");
					 							else
					 								out.println("<td></td>");
					 							out.println("<td>"+transaction.getTransactionDate()+"</td>"); 
					 							out.println("<td>"+transaction.getTransactionType()+"</td>"); 
					 							out.println("<td>"+transaction.getAmount()+"</td>"); 
					 							
					 							out.println("</tr>"); 
					 							 
					 						} 
				 }
				session.setAttribute("r", 0);
				out.println("</table>\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

}
