package com.kosta.bank.dao;

import java.util.List;

import com.kosta.bank.bean.Account;

public interface AccountDAO {
	
	//계좌개설
	void insertAccount(Account acc) throws Exception;

	//입금,출금
	void updateAccount(Account acc) throws Exception;

	//계좌조회
	Account selectAccount(String id) throws Exception;

	//전체계좌 조회
	List<Account> selectAccountList() throws Exception;
}
