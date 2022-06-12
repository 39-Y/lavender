package com.spring.LAB.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) 
	 throws Exception;
	public ModelAndView register(HttpServletRequest request, MemberVO memberVO) 
	 throws Exception;
	public ModelAndView modMember(HttpServletRequest request, MemberVO memberVO) 
			 throws Exception;
	public ModelAndView deleteMember(HttpServletRequest request) 
			 throws Exception;
	ModelAndView admin(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
