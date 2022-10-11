package com.kosta.bank.dao;

import com.kosta.bank.bean.Account;

public interface AccountDAO {
	
	//���°���
	void insertAccount(Account acc) throws Exception;

	//�Ա�,���
	void updateAccount(Account acc) throws Exception;

	//������ȸ
	Account selectAccount(String id) throws Exception;
}
