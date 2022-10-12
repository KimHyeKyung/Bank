package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.bean.Account;
import com.kosta.bank.dao.AccountDAO;

@Service
public class AccountService {
	
	@Autowired
	AccountDAO accountDAO;
	
	//계좌번호 중복체크
	public boolean isDoubleAccountId(String id) throws Exception{
		Account account = accountDAO.selectAccount(id);
		if(account==null) return false;
		return true;
	}
	
}
