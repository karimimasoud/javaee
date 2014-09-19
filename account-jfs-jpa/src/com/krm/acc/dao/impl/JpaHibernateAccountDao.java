package com.krm.acc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.krm.acc.dao.AccountDao;
import com.krm.acc.domain.Account;

public class JpaHibernateAccountDao implements AccountDao {

	@Override
	public void add(Account account) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("unit1");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(account);
			System.out.println("1 row added to account");
			entityManager.getTransaction().commit();
			entityManager.close();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Account> getAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountByAccountID(Long accountID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
