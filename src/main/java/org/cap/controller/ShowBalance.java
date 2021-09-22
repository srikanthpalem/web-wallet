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


@WebServlet("/ShowBalance")
public class ShowBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ICustomerService service=new CustomerServiceImpl();
    		
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		Integer custId=(Integer)session.getAttribute("custId");
		if(session.getAttribute("r")==null)
			session.setAttribute("r", 0);
		if(session.getAttribute("accno")==null)
			session.setAttribute("accno", 0);
		List<Account> accounts=service.getAccounts(custId);
		PrintWriter out=response.getWriter();
		out.println("<html>" + 
				"<head>" + 
				"<meta charset='ISO-8859-1'>" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<link href='css/mainPage.css' rel='stylesheet' type='text/css'>"+
				"<form action='ShowBalanceServlet' method='post'>\r\n" + 
				"<div id='accCnt'>"+
				"<p>"+
				"<h3 align=\"center\" style='color:blue;'>Show Balance</h3>"+
				"<table>\r\n" + 
				"	<tr>\r\n" + 
				"		<th>choose account</th>\r\n" + 
				"		<td><select name='accountno' id='dynamicaccount'>\r\n" );
		Long accno=Long.parseLong((session.getAttribute("accno").toString()));
		for(Account account:accounts){
					if(accno==account.getAccountNo()&&(Integer)session.getAttribute("r")==1)
					out.println("<option value='"+account.getAccountNo()+"' selected>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
					else
						out.println("<option value='"+account.getAccountNo()+"'>"+account.getAccountNo()+" - "+account.getAccountType()+"</option>");
				}		 
				out.println("</select>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 	
				"	<tr>\r\n" + 
				"	<td colspan='2' align='center'><input type='submit' name='submit' value='show balance' class='btnClass'></td>\r\n" + 
				"	</tr>\r\n" + 
				"   <tr>\r\n"+
				"    <td>Balance</td>");
				
				HttpSession session1=request.getSession(false);
				if(session1.getAttribute("finalbalance")==null||(Integer)session1.getAttribute("r")==0)
					session1.setAttribute("finalbalance", 0);
				double balance=Double.parseDouble(session1.getAttribute("finalbalance").toString());
				out.println("    <td><input type='text' name='balance' value='"+balance+"'></td>\r\n"+
				"</table>\r\n" +  
				"</div>"+
				"</form>\r\n" + 
				"	\r\n" + 
				"</body>\r\n" + 
				"</html>");
				session1.setAttribute("r", 0);
		
	}

	
	
}
