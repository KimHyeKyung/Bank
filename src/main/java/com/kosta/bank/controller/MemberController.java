package com.kosta.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.bean.Member;
import com.kosta.bank.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value = "/login")
	String login(Model model) {
		model.addAttribute("page", "login_form");
		return "main";
	}
	
	@RequestMapping(value = "/join")
	String join(Model model) {
		model.addAttribute("page", "join_form");
		return "main";
	}
	
	//회원가입 수행
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	String join(@ModelAttribute Member member ,Model model) {
		try {
			memberService.joinMember(member);
			model.addAttribute("page", "login_form");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "회원가입 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//로그인 수행
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
		try {
			if(memberService.login(id,password)) {//로그인성공
				session.setAttribute("id", id);
				model.addAttribute("page", "makeaccount_form");
			}else throw new Exception("로그인 실패");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "로그인 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	String logout(Model model) {
		try {
			session.removeAttribute("id");
			model.addAttribute("page","login_form");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	//아이디 중복체크
	//ajax를 사용할때는 @ResponseBody를 써야한다.
	@ResponseBody
	@RequestMapping(value = "/mid", method = RequestMethod.POST)
	String mid(@RequestParam("id") String id) {
		try {
			if(memberService.isDoubleId(id)) {
				return "true"; //ture면 중복된 아아디
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";//false면 사용가능한 아이디
	}
	
	
}
