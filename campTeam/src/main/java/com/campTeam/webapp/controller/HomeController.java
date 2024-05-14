package com.campTeam.webapp.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		
		log.info("home");
		model.addAttribute("arg", "인자");
		
		return "home"; 
		// return "redirect:/member/join.do";
	} //
	
	@GetMapping("/about.do")
	public String about() {
		
		log.info("소개");
		
		return "about";
	}
	
	@GetMapping("/content1.do")
	public String content1() {
		
		log.info("공지사항");
		
		return "content1";
	} //
	
	@GetMapping("/searchlist.do")
	public String content2() {
		
		log.info("추천검색");
		
		return "content2";
	} //
	
	@GetMapping("/login")
	public String login() {
		
		log.info("login");
		return "login";
	} //
	
	@GetMapping("/welcome")
	public String welcome() {
		
		log.info("welcome");
		return "welcome";
	} //
	
	@GetMapping("/loginError")
    public String loginError(Model model, HttpSession session) {
    	
		// Spring CustomProvider 인증(Auth) 에러 메시지 처리
		Object secuSess = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	
		log.info("#### 인증 오류 메시지-1 : " + secuSess);
		log.info("#### 인증 오류 메시지-2 : " + secuSess.toString());
	
		model.addAttribute("error", "true");
		model.addAttribute("msg", secuSess);
	
		return "login";
	}	
	
	// /403 : 접근 권한 문제시 이동 페이지 : SecurityConfig.java
	/*
	   .and()
           .exceptionHandling().accessDeniedPage("/403");     
	 */
	@GetMapping("/403")
    public String acessDenided(Model model, HttpSession session) {
		
		log.error("403 mapping");
    	
		model.addAttribute("errMsg", "페이지 접근 권한이 없습니다.");
		model.addAttribute("movePage", "/welcome");
	
		return "/error/error";
	}
	

}
