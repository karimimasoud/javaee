package com.krm.acc.dao;

import com.krm.acc.dao.impl.InMemoryAccountDao;


public class AccountDaoFactory {
	
	private static AccountDao accountDao;
	
	public static AccountDao createAccountDao (){
		
		if (accountDao == null){
			
			accountDao = new InMemoryAccountDao();
			System.out.println("accountDao was initiliazed ....");
		}
		
		return accountDao;
	}

}
