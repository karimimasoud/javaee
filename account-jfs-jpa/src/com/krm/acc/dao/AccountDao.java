package com.krm.acc.dao;

import java.util.List;

import com.krm.acc.domain.Account;

public interface AccountDao {

	void add(Account account);

	void update(Account account);

	void delete(Account account);

	List<Account> getAccount();

	Account getAccountByAccountID(Long accountID);

}
