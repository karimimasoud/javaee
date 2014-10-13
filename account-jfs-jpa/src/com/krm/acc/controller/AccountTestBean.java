package com.krm.acc.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.krm.acc.domain.Account;
import com.krm.acc.dao.impl.JpaHibernateAccountDao;

import org.hsqldb.lib.HashMap;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "accountBean", eager = true)
@javax.faces.bean.ViewScoped
public class AccountTestBean {

	private long id = 0;
	private String accountName = "";
	private long balance = 0;
	private String str = "test";

	private List<Account> accounts = null;

	private TreeNode accountRoot = null;

	private TreeNode selectedNode;

	public String getStr() {
		System.out.println("get str: " + str);
		return str;
	}

	public void setStr(String str) {
		this.str = str;
		System.out.println("set str: " + str);
	}

	@PostConstruct
	public void init() {
		this.accounts = new JpaHibernateAccountDao().getAccount();
		//this.accounts = getAccounts();
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

		
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public TreeNode getAccountRoot() {
		this.accountRoot = new DefaultTreeNode("Root", null);
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

	public void onNodeExpand(NodeExpandEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Expanded", event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onNodeSelect(NodeSelectEvent event) {
		TreeNode subNode = null;

		long currentNodeAccountId = Long.parseLong(event.getTreeNode()
				.getData().toString());

		for (Account account : accounts) {
			if (account.getParentAccountID() != null  )
				if (account.getParentAccountID() == currentNodeAccountId)
				subNode = new DefaultTreeNode((Account) account,
						event.getTreeNode());
		}

		System.out.println(event.getTreeNode().getData().toString());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selected", event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Account Edited", ((Account) event.getObject()).getAccountName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        Account account = (Account) event.getObject();
        updateAccount(account);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Account) event.getObject()).getAccountName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void updateAccount (Account account){
    	JpaHibernateAccountDao jpaHibernateAccountDao = new JpaHibernateAccountDao();
		jpaHibernateAccountDao.update(account);
    }
}
