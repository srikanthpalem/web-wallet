package org.cap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.CustomerServiceImpl;
import org.cap.service.ICustomerService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ICustomerService customerService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		customerService = new CustomerServiceImpl();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String custPassword = request.getParameter("custPassword");
		String doorNo = request.getParameter("doorNo");
		String streetname = request.getParameter("streetname");
		String city = request.getParameter("city");

		String pincode = request.getParameter("pincode");

		String state = request.getParameter("state");

		Address address = new Address();
		address.setCity(city);
		address.setDoorNo(doorNo);
		address.setPincode(pincode);
		address.setState(state);
		address.setStreetName(streetname);

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmailId(email);
		customer.setMobileNo(mobile);
		customer.setCustPassword(custPassword);
		// System.out.println(dateOfBirth);
		Date custDate;
		try {
			custDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
			customer.setDateOfBirth(custDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customer.setAddress(address);

		if (customerService.createCustomer(customer)) {
			request.getRequestDispatcher("index.html").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<div style='color:red;'>Customer Saved Successfully!</div>");

		} else {
			request.getRequestDispatcher("index.html").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<div style='color:red;'>Invalid Customer Details!</div>");

		}

	}

}
