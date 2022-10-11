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
	
	//�Լ��� �޼��� �����ε��� ���ؼ� �Լ����� �Ȱ��Ƶ� �Ű������� ������ Ÿ�Կ� ���� �ٸ� �޼���� �νĵȴ�.
	@RequestMapping(value = "/make_account", method = RequestMethod.POST)
	String makeAccount(HttpServletRequest request, Model model) {
		//�ݵ�� ��Ʈ�ѷ����� ����ó���� �ؾ��Ѵ�.
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
			model.addAttribute("err", "���°��� ����");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//�Ա�������
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	String deposit(Model model) {
		model.addAttribute("page", "deposit_form");
		return "main";
	}
	
	//�Աݼ���
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
			model.addAttribute("err", "�Ա� ����");
			model.addAttribute("page", "err");
		}
		
		return "main";
	}
	
	//���������
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	String withdraw(Model model) {
		model.addAttribute("page", "withdraw_form");
		return "main";
	}
	
	//��ݼ���
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
			model.addAttribute("err", "��� ����");
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
