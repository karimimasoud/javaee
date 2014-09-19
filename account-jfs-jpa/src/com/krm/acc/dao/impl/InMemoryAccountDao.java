package com.krm.acc.dao.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.krm.acc.dao.AccountDao;
import com.krm.acc.domain.Account;

public class InMemoryAccountDao implements AccountDao {

	private Hashtable<String, Account> accounts;

	// public void createAccountDao() {
	public void InMemoryAccountDao() {
		System.out.println("new Hashtable was created");
		accounts = new Hashtable<String, Account>();
	}

	@Override
	public void add(Account account) {
		// TODO Auto-generated method stub
		if (accounts == null) {
			this.InMemoryAccountDao();
		}
		accounts.put(account.getAccountID().toString(), account);
		//accounts.put("a", account);
		System.out.println("account: " + account.getAccountID().toString()
				+ " , name: " + account.getAccountName() + " was added");
		//System.out.println(account.getAccountName() + " was added ********");

	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		accounts.put(account.getAccountID().toString(), account);
		System.out.println("account: " + account.getAccountID().toString()
				+ " , name: " + account.getAccountName() + " was updated");

	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		accounts.remove(account.getAccountID().toString());
		System.out.println("account: " + account.getAccountID().toString()
				+ " , name: " + account.getAccountName() + " was deleted");

	}

	@Override
	public List<Account> getAccount() {
		// TODO Auto-generated method stub
		return new ArrayList<Account>(accounts.values());
	}

	@Override
	public Account getAccountByAccountID(Long accountID) {
		// TODO Auto-generated method stub
		return accounts.get(accountID);
	}

}
