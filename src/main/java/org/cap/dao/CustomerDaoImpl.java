package org.cap.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.model.Transaction;

public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public boolean createCustomer(Customer customer) {
		
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();
			entityManager.persist(customer.getAddress());
			entityManager.persist(customer);
		
		transaction.commit();
		entityManager.close();
		
		if(customer.getCustomerId()>0)
			return true;
		return false;
	}

	
	private EntityManager getEntityManager() {
		EntityManagerFactory emf=
				Persistence.createEntityManagerFactory("jpademo");
		return emf.createEntityManager();
	}


	@Override
	public boolean createAccount(Account account) {
		
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();
		
		account.setOpeningDate(new Date());
		
		entityManager.persist(account);
		
		transaction.commit();
		entityManager.close();
		
		
		if(account.getAccountNo()>0)
			return true;
		return false;
		
		
	}


	@Override
	public Customer findCustomer(Integer custId) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();
		
		Customer customer=entityManager.find(Customer.class, custId);
		
		
		
		transaction.commit();
		entityManager.close();
		return customer;
	}
	
	@Override
	public Customer isValidLogin(Customer customer) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();

		transaction.begin();

		
		String sql="from Customer cust where cust.emailId=:uName and cust.custPassword=:uPwd";

		Query query= entityManager.createQuery(sql);

		query.setParameter("uName", customer.getEmailId());

		query.setParameter("uPwd", customer.getCustPassword());

		

		List<Customer> logins= query.getResultList();

		transaction.commit();

		entityManager.close();

		

		if(logins.size()>0) 

			return logins.get(0);

		

		return null;

	}


	@Override
	public List<Account> getAccounts(int customerId) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();

		transaction.begin();
		String sql=null;
		Query query=null;
		if(customerId!=-100) {
			sql="select cust.accounts from Customer cust where cust.customerId=:custId";
			query= entityManager.createQuery(sql);
			query.setParameter("custId", customerId);	
		}
		else {
			sql="from Account";
			query= entityManager.createQuery(sql);

		}

		

		List<Account> accounts= query.getResultList();

		transaction.commit();

		entityManager.close();

		return accounts;
	}


	@Override
	public Account findAccount(long accountno) {
				EntityManager entityManager= getEntityManager();
				EntityTransaction transaction= entityManager.getTransaction();
				transaction.begin();
				
				Account account1=entityManager.find(Account.class, accountno);
				
				
				
				transaction.commit();
				entityManager.close();
				return account1;
	}


	@Override
	public boolean createTransaction(Transaction transaction1) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();

		transaction.begin();
	
		entityManager.persist(transaction1);		
	
		transaction.commit();

		entityManager.close();

		if(transaction1.getTransactionId()>0)
			return true;
		return false;
	}


	@Override
	public double getTotal(Account account) {
		
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();

		String sql="select sum(t.amount) from Transaction t where t.fromAccount=:fromacc";
		
		Query query= entityManager.createQuery(sql);
		query.setParameter("fromacc", account);
		double fromtotal=0;
		List<Double> a=query.getResultList();
		//if(
		try {
			fromtotal=a.get(0);
		} catch (Exception e) {
			fromtotal=0;
		}
		String sql1="select sum(t.amount) from Transaction t where t.toAccount=:toacc";
		
		Query query1= entityManager.createQuery(sql1);
		query1.setParameter("toacc", account);
		double tototal=0;
		List<Double> a1=query1.getResultList();
		//if(
		try {
			tototal=a1.get(0);
		} catch (Exception e) {
			tototal=0;
		}
		double amount=tototal-fromtotal;		
	    transaction.commit();
		entityManager.close();
		return amount;
	}


	@Override
	public List<Transaction> getTransactionsFromTo(Account account, LocalDate fromDate, LocalDate toDate) {
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction= entityManager.getTransaction();
		transaction.begin();
		String sql="from Transaction t where (t.fromAccount=:acct or t.toAccount=:acct) and t.transactionDate between'"+fromDate+"'and'"+toDate+"'";
		Query query1= entityManager.createQuery(sql);
		query1.setParameter("acct", account);
		List<Transaction> transactions=query1.getResultList();
		return transactions;
	}



	
	
	
}
