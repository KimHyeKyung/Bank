package com.kosta.bank.dao;

import com.kosta.bank.bean.Account;

public interface AccountDAO {
	void insertAccount(Account acc) throws Exception;

	void deposit(Account acc) throws Exception;

	void withdraw(Account acc) throws Exception;
}
