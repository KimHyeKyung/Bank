package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.bean.Member;
import com.kosta.bank.dao.MemberDAO;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;

	//회원가입
	public void joinMember(Member member) throws Exception{
		Member emember = memberDAO.selectMember(member.getId());
		if(emember != null) throw new Exception("계좌번호 중복");
		memberDAO.join(member);
	}
	
	//비밀번호 확인
	public boolean login(String id, String passoword) throws Exception{
		Member emember = memberDAO.selectMember(id);
		if(emember==null) throw new Exception("아이디 오류");
		if(!emember.getPassword().equals(passoword)) return false;
		
		return true;
	}
	
	//아이디 중복체크
	public boolean isDoubleId(String id) throws Exception{
		Member emember = memberDAO.selectMember(id);
		if(emember==null) return false;
		return true;
	}

}
