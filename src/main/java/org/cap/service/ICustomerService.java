package org.cap.service;

import java.time.LocalDate;
import java.util.List;

import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.model.Transaction;

public interface ICustomerService {
	public Customer isValidLogin(Customer customer);
	
	public boolean createCustomer(Customer customer);

	public boolean createAccount(Account account);

	public Customer findCustomer(Integer custId);
	
	public List<Account> getAccounts(int customerId);

	public Account findAccount(long accountno);

	public boolean createTransaction(Transaction transaction);

	//public List<Transaction> getTransactions(long accountNo);

	public double getTotal(Account account);

	public List<Transaction> getTransactionsFromTo(Account account, LocalDate fromDate, LocalDate toDate);
}
