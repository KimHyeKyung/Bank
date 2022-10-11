package com.kosta.bank.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.bean.Account;

public class AccountDAOImpl implements AccountDAO{

	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
		
	}

	@Override
	public void deposit(Account acc) throws Exception {
		sqlSession.update("mapper.account.deposit", acc);
	}

	@Override
	public void withdraw(Account acc) throws Exception {
		sqlSession.update("mapper.account.withdraw", acc);
	}

}
