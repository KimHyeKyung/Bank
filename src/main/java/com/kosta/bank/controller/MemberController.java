package com.kosta.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

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
}
