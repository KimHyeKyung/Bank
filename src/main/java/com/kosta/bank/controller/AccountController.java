package com.kosta.bank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.bean.Account;
import com.kosta.bank.dao.AccountDAO;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	AccountService accountService;


	//로그인
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String main(Model model) {
		model.addAttribute("page","login_form");
		return "main";
	}
	
	//계좌개설 페이지
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
	String deposit(Model model, @RequestParam("id") String id, @RequestParam("money") int money) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc == null) throw new Exception("계좌번호 오류");
			acc.deposit(money);
			accountDAO.updateAccount(acc);
			model.addAttribute("id",acc.getId());
			model.addAttribute("money",money);
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
	String withdraw(Model model, @RequestParam("id") String id, @RequestParam("money") int money) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc == null) throw new Exception("계좌번호 오류");
			if(acc.getBalance() < money) {
				throw new Exception("잔액 부족");
			}else {
				acc.withdraw(money);
			}
			accountDAO.updateAccount(acc);
			model.addAttribute("id",acc.getId());
			model.addAttribute("money",money);
			model.addAttribute("page","withdraw_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "출금 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//계좌조회 페이지
	@RequestMapping(value = "/accinfo", method = RequestMethod.GET)
	String accinfo(Model model) {
		model.addAttribute("page", "accinfo_form");
		return "main";
	}
	
	//계좌조회 수행
	@RequestMapping(value = "/acc_info", method = RequestMethod.POST)
	String accinfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc==null) throw new Exception();
			model.addAttribute("acc", acc);
			model.addAttribute("page","accinfo_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "계좌조회 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//전체계좌 조회
	@RequestMapping(value = "/allaccinfo", method = RequestMethod.GET)
	String allaccinfo(Model model) {
		try {
			List<Account> accs = accountDAO.selectAccountList();
			model.addAttribute("accs", accs);
			model.addAttribute("page","allaccinfo_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "전체계좌조회 실패");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	
	//계좌 중복체크
	//ajax를 사용할때는 @ResponseBody를 써야한다.
	@ResponseBody
	@RequestMapping(value = "/accountId", method = RequestMethod.POST)
	String accountId(@RequestParam("id") String id) {
		try {
			if(accountService.isDoubleAccountId(id)) {
				return "true"; //ture면 중복된 계좌
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";//false면 사용가능한 계좌
	}
		
	
	
}
