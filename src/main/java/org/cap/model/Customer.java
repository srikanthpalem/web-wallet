package org.cap.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String custPassword;
	private Date dateOfBirth;
	@OneToOne
	@JoinColumn(name = "address_fk")
	private Address address;

	@OneToMany(mappedBy = "customer", targetEntity = Account.class)
	private List<Account> accounts = new ArrayList<>();

	public Customer() {

	}

	public Customer(String emailId, String custPassword) {
		super();
		this.emailId = emailId;
		this.custPassword = custPassword;
	}

	public Customer(int customerId, String firstName, String lastName, String mobileNo, String emailId,
			Date dateOfBirth, Address address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.address = address;

	}

	public Customer(String firstName, String lastName, String mobileNo, String emailId, Date dateOfBirth,
			Address address) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.address = address;

	}

	public Customer(int customerId, String firstName, String lastName, String mobileNo, String emailId,
			Date dateOfBirth, Address address, List<Account> accounts) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.accounts = accounts;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
