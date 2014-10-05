package com.krm.acc.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.krm.acc.domain.Account;
import com.krm.acc.dao.impl.JpaHibernateAccountDao;

import org.hsqldb.lib.HashMap;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

@ManagedBean(name = "accountBean", eager = true)
public class AccountTestBean {

	private long id = 0;
	private String accountName = "";
	private long balance = 0;
	private String str = "test";

	private List<Account> accounts = null;

	private TreeNode accountRoot = null;

	public String getStr() {
		System.out.println("get str: " + str);
		return str;
	}

	public void setStr(String str) {
		this.str = str;
		System.out.println("set str: " + str);
	}

	public String addAccount() {
		Account account = new Account();
		account.setAccountID((long) (accounts.size() + 1));
		account.setAccountName(this.accountName);
		account.setBalance(this.balance);

		JpaHibernateAccountDao jpaHibernateAccountDao = new JpaHibernateAccountDao();
		jpaHibernateAccountDao.add(account);

		return "accounts";
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

	public List<Account> getAccounts() {

		this.accounts = new JpaHibernateAccountDao().getAccount();
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public TreeNode getAccountRoot() {
		this.accountRoot = new DefaultTreeNode("Root", null);
		this.accounts = getAccounts();
		TreeNode node = null;// new DefaultTreeNode("node 1", this.accountRoot);
		TreeNode subNode = null;

		for (Account account : accounts) {
			if (account.getParentAccountID() == null) {
				node = new DefaultTreeNode((Account) account, this.accountRoot);
				for (Account subAccount : accounts)
					if (subAccount.getParentAccountID() == account
							.getAccountID())
						subNode = new DefaultTreeNode((Account) subAccount,
								node);

			}
		}
		// accountRoot.getChildren().add(acNode);
		return this.accountRoot;
	}

	public void setAccountRoot(TreeNode accountRoot) {
		this.accountRoot = accountRoot;
	}

}
