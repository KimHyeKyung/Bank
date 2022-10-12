package com.kosta.bank.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.bean.Member;

public class MemberDAOImpl  implements MemberDAO{

private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//ȸ������ ����
	@Override
	public void join(Member member) throws Exception {
		sqlSession.insert("mapper.member.join", member);
		
	}

	//�α��� ����
	@Override
	public Member login(Member member) throws Exception {
		return sqlSession.selectOne("mapper.member.login", member);
	}

	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember", id);
	}

}
