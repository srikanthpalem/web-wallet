package org.cap.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.cap.util.AccountType;

@Entity
//@SequenceGenerator(name="seq",initialValue=10000)
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long accountNo;
	private double openingBalance;
	private AccountType accountType;
	private Date openingDate;
	private String description;
	
	@OneToMany(mappedBy= "fromAccount",targetEntity=Transaction.class,
			cascade=CascadeType.REFRESH)
	private List<Transaction> transactions=new ArrayList<>();
	
	@OneToMany(mappedBy= "toAccount",targetEntity=Transaction.class,
			cascade=CascadeType.REFRESH)
	private List<Transaction> toTransactions=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="customer_fk")
	private Customer customer;
	
	public Account() {
		
	}
	
	public Account(long accountNo, double openingBalance, AccountType accountType, Date openingDate,
			String description, Customer customer) {
		super();
		this.accountNo = accountNo;
		this.openingBalance = openingBalance;
		this.accountType = accountType;
		this.openingDate = openingDate;
		this.description = description;
		this.customer = customer;
	}
	
	
	
	public List<Transaction> getToTransactions() {
		return toTransactions;
	}

	public void setToTransactions(List<Transaction> toTransactions) {
		this.toTransactions = toTransactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", openingBalance=" + openingBalance + ", accountType=" + accountType
				+ ", openingDate=" + openingDate + ", description=" + description + "]";
	}
	
	

}
