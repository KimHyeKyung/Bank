package com.kosta.bank.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.bank.bean.Account;
import com.kosta.bank.dao.AccountDAO;

@Controller
public class AccountController {

	@Autowired
	AccountDAO accountDAO;
	
//	public void setAccountDAO(AccountDAO accountDAO) {
//		this.accountDAO = accountDAO;
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String main(Model model) {
		model.addAttribute("page","login_form");
		return "main";
	}
	
	@RequestMapping(value = "/makeaccount", method = RequestMethod.GET)
	String makeAccount(Model model) {
		model.addAttribute("page", "makeaccount_form");
		return "main";
	}
	
	//함수는 메서드 오버로딩에 의해서 함수명이 똑같아도 매개변수의 개수나 타입에 따라서 다른 메서드로 인식된다.
	@RequestMapping(value = "/make_account", method = RequestMethod.POST)
	String makeAccount(HttpServletRequest request, Model model) {
		//반드시 컨트롤러에서 예외처리를 해야한다.
		try {
			Account acc = new Account();
			acc.setId(request.getParameter("id"));
			acc.setName(request.getParameter("name"));
			acc.setBalance(Integer.parseInt(request.getParameter("balance")));
			acc.setSect(request.getParameter("sect"));
			if(acc.getSect().equals("normal")) {
				acc.setGrade(null);
			}else {
				acc.setGrade(request.getParameter("grade"));
			}
			accountDAO.insertAccount(acc);
			model.addAttribute("acc",acc);
			model.addAttribute("page","makeaccount_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "계좌개설 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//입금페이지
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	String deposit(Model model) {
		model.addAttribute("page", "deposit_form");
		return "main";
	}
	
	//입금수행
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	String deposit(HttpServletRequest request ,Model model, @ModelAttribute Account acc) {
		try {
			int money = Integer.parseInt(request.getParameter("money"));
			int balance = money;
			acc.setBalance(balance);
			accountDAO.deposit(acc);
			model.addAttribute("acc",acc);
			model.addAttribute("page","deposit_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "입금 실패");
			model.addAttribute("page", "err");
		}
		
		return "main";
	}
	
	//출금페이지
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	String withdraw(Model model) {
		model.addAttribute("page", "withdraw_form");
		return "main";
	}
	
	//출금수행
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	String withdraw(HttpServletRequest request, Model model, @ModelAttribute Account acc) {
		try {
			int money = Integer.parseInt(request.getParameter("money"));
			int balance = money;
			acc.setBalance(balance);
			accountDAO.withdraw(acc);
			model.addAttribute("acc",acc);
			model.addAttribute("page","withdraw_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "출금 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	@RequestMapping(value = "/accinfo", method = RequestMethod.GET)
	String accinfo(Model model) {
		model.addAttribute("page", "accinfo_form");
		return "main";
	}
	
}
