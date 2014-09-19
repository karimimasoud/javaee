package com.krm.acc.domain;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	private Long accountID;
	
	@Column (name="account_name", length=25)
	private String accountName;
	
	@Column (name="balance")
	private Long balance;
	
	@Column (name="parent_account_id")
	private Long parentAccountID;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long iD) {
		accountID = iD;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getParentAccountID() {
		return parentAccountID;
	}

	public void setParentAccountID(Long parentAccountID) {
		this.parentAccountID = parentAccountID;
	}

}
