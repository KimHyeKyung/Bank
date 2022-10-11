package com.kosta.bank.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.bean.Account;

public class AccountDAOImpl implements AccountDAO{

	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	//계좌개설
	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
	}

	//입금,출금
	@Override
	public void updateAccount(Account acc) throws Exception {
		sqlSession.update("mapper.account.updateAccount", acc);
	}

	//계좌조회
	@Override
	public Account selectAccount(String id) throws Exception {
		return sqlSession.selectOne("mapper.account.selectAccount", id);
	}

	@Override
	public List<Account> selectAccountList() throws Exception {
		return sqlSession.selectList("mapper.account.selectAccountList");
	}


}
