package org.cap.service;

import java.time.LocalDate;
import java.util.List;

import org.cap.dao.CustomerDaoImpl;
import org.cap.dao.ICustomerDao;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.model.Transaction;

public class CustomerServiceImpl implements ICustomerService {

	private ICustomerDao customerDao;
	
	public CustomerServiceImpl() {
		customerDao=new CustomerDaoImpl();
	}
	
	@Override
	public boolean createCustomer(Customer customer) {
		
		return customerDao.createCustomer(customer);
	}

	@Override
	public boolean createAccount(Account account) {
		return customerDao.createAccount(account);
	}

	@Override
	public Customer findCustomer(Integer custId) {
		return customerDao.findCustomer(custId);
	}
	@Override
	public Customer isValidLogin(Customer customer) {
		return customerDao.isValidLogin(customer);
	}

	@Override
	public List<Account> getAccounts(int customerId) {
		return customerDao.getAccounts(customerId);
	}

	@Override
	public Account findAccount(long accountno) {
		return customerDao.findAccount(accountno);
	}

	@Override
	public boolean createTransaction(Transaction transaction) {
		return customerDao.createTransaction(transaction);
	}

	@Override
	public double getTotal(Account account) {
		double balance=customerDao.getTotal(account)+account.getOpeningBalance();
		return balance;
	}

	@Override
	public List<Transaction> getTransactionsFromTo(Account account, LocalDate fromDate, LocalDate toDate) {
		return customerDao.getTransactionsFromTo( account,  fromDate,  toDate);
	}

//	@Override
//	public List<Transaction> getTransactions(long accountNo) {
//		// TODO Auto-generated method stub
//		return customerDao.getTransactions(accountNo);
//	}

}
