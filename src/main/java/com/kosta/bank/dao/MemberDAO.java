package com.kosta.bank.dao;

import com.kosta.bank.bean.Member;

//인터페이스는 기본이 public
public interface MemberDAO {
	
	//회원가입 수행
	void join(Member member) throws Exception;

	//로그인 수행
	Member login(Member member) throws Exception;

	Member selectMember(String id) throws Exception;

	
}
