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


@WebServlet("/DepositWithdrawal")
public class DepositWithdrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ICustomerService service=new CustomerServiceImpl();    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		Integer custId=(Integer)session.getAttribute("custId");
		List<Account> accounts=service.getAccounts(custId);
		PrintWriter out=response.getWriter();
		
		out.println("<html>" + 
				"<head>" + 
				"<meta charset='ISO-8859-1'>" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" +
				"<h3 align=\"center\" style=\"color:blue;\">Deposit / Withdraw</h3>"+
				"<link href='css/mainPage.css' rel='stylesheet' type='text/css'>"+
				"<form action='DepositWithdrawalLogicServlet' method='post'>\r\n" + 
				"<div id='accCnt'>"+
				"<p><input type='radio' value='deposit' style='color:blue;' name='type' checked >deposit\r\n" + 
				"<input type='radio' value='withdraw' name='type'>withdraw\r\n" + 
				"</p>\r\n" + 
				"<table>\r\n" + 
				"	<tr>\r\n" + 
				"		<th>choose account</th>\r\n" + 
				"		<td><select name='accountno' id='dynamicaccount'>\r\n" );
				for(Account account:accounts){
					out.println("<option value='"+account.getAccountNo()+"'>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
				}		 
				out.println("</select>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<th>amount</th>\r\n" + 
				"		<td><input type='text' name='amount' required ></td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"		<th>desc:</th>\r\n" + 
				"		<td><input type='text' name='desc' required ></td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr>\r\n" + 
				"	<td colspan='2' align='center'><input type='submit' name='submit' value='submit' class='btnClass'></td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>\r\n" + 
				"</div>"+
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	
		
		
		
	}

	
}
