package com.krm.acc.controller;

import javax.faces.bean.ManagedBean;
import com.krm.acc.domain.Account;
import com.krm.acc.dao.impl.JpaHibernateAccountDao;

@ManagedBean(name="accountBean", eager = true)
public class AccountTestBean {


	private long id = 0;
	private String accountName = "";
	private long balance = 0;
	private String str = "test";
	
	public String getStr() {
		System.out.println("get str: " + str);
		return str;
	}
	public void setStr(String str) {
		this.str = str;
		System.out.println("set str: " + str);
	}
	
	public String addAccount()
	{
		Account account = new Account();
		account.setAccountID(this.id);
		account.setAccountName(this.accountName);
		account.setBalance(this.balance);
		
		JpaHibernateAccountDao jpaHibernateAccountDao = new JpaHibernateAccountDao();
		jpaHibernateAccountDao.add(account);
		
		return "account";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
}
