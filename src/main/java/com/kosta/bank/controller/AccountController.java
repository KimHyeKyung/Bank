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


	//�α���
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String main(Model model) {
		model.addAttribute("page","login_form");
		return "main";
	}
	
	//���°��� ������
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
	String deposit(Model model, @RequestParam("id") String id, @RequestParam("money") int money) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc == null) throw new Exception("���¹�ȣ ����");
			acc.deposit(money);
			accountDAO.updateAccount(acc);
			model.addAttribute("id",acc.getId());
			model.addAttribute("money",money);
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
	String withdraw(Model model, @RequestParam("id") String id, @RequestParam("money") int money) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc == null) throw new Exception("���¹�ȣ ����");
			if(acc.getBalance() < money) {
				throw new Exception("�ܾ� ����");
			}else {
				acc.withdraw(money);
			}
			accountDAO.updateAccount(acc);
			model.addAttribute("id",acc.getId());
			model.addAttribute("money",money);
			model.addAttribute("page","withdraw_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "��� ����");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//������ȸ ������
	@RequestMapping(value = "/accinfo", method = RequestMethod.GET)
	String accinfo(Model model) {
		model.addAttribute("page", "accinfo_form");
		return "main";
	}
	
	//������ȸ ����
	@RequestMapping(value = "/acc_info", method = RequestMethod.POST)
	String accinfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountDAO.selectAccount(id);
			if(acc==null) throw new Exception();
			model.addAttribute("acc", acc);
			model.addAttribute("page","accinfo_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "������ȸ ����");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	//��ü���� ��ȸ
	@RequestMapping(value = "/allaccinfo", method = RequestMethod.GET)
	String allaccinfo(Model model) {
		try {
			List<Account> accs = accountDAO.selectAccountList();
			model.addAttribute("accs", accs);
			model.addAttribute("page","allaccinfo_success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "��ü������ȸ ����");
			model.addAttribute("page", "err");
		}
		return "main";
	}
	
	
	//���� �ߺ�üũ
	//ajax�� ����Ҷ��� @ResponseBody�� ����Ѵ�.
	@ResponseBody
	@RequestMapping(value = "/accountId", method = RequestMethod.POST)
	String accountId(@RequestParam("id") String id) {
		try {
			if(accountService.isDoubleAccountId(id)) {
				return "true"; //ture�� �ߺ��� ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";//false�� ��밡���� ����
	}
		
	
	
}
