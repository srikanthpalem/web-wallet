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
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;

@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ICustomerService service=new CustomerServiceImpl();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		Integer custId=(Integer)session.getAttribute("custId");
		List<Account> accounts=service.getAccounts(custId);
		List<Account> accounts1=service.getAccounts(-100);
		PrintWriter out= response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<form method=\"post\" action='FundTransferLogic'>\r\n" + 
				"	<table cellpadding=\"5px\">\r\n" + 
				"		<tr>\r\n" + 
				"			<th>from account</th>\r\n" + 
				"			<td><select name=\"from account\">\r\n" );
		for(Account account:accounts){
			out.println("<option value='"+account.getAccountNo()+"'>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
	}
		
				out.println("			</select>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>to account</th>\r\n" + 
				"			<td><select name=\"to account\">\r\n" );
				for(Account account:accounts1){
					out.println("<option value='"+account.getAccountNo()+"'>"+account.getCustomer().getCustomerId()+"-"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
			}
		
				out.println("			</select>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>amount:</th>\r\n" + 
				"			<td><input type=\"text\" name='amount' required ></td>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>desc:</th>\r\n" + 
				"			<td><input type=\"text\" name=\"desc\" required ></td>\r\n" + 
				"		</tr>\r\n" +
				"		<tr>\r\n" + 
				"			<td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"submit\"></td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

}
