package com.gym.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mariadb.jdbc.internal.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.domain.UserVO;
import com.gym.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/*===========================
	 * 
	 * 1. 프로필 등록 
	 * 2. 회원가입,회원처리,남은일수등
	 * 3. 운영자 아이디, 선생님들 아이디 프로
	 * 4. 내정보 교체, 뭐 .. 등등 ~ 
	 * 
	 * 
	 * */
	
	
	
	
	@Inject
	private UserService service;
	
	@GetMapping("/login")
	public void getLogin() {
		System.out.println("get Login");
	}
	@PostMapping("/login")
	public String postLogin(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		
		UserVO login = service.login();
		HttpSession session = req.getSession();
		
		if(login != null) {
			session.setAttribute("user", login);
			return "redirect:/";
		}
		else {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/user/login";
		}
	}
}
