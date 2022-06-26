package com.spring.LAB.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.domain.member.Role;
import com.spring.LAB.member.service.GuestJpaService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MemberIndexContorller {
	private final GuestJpaService guestJpaService;
	
	@GetMapping(value="/nidlogin")
	public ModelAndView logInPage(@LoginMember SessionMember member) throws Exception {
		String setViewName = member==null? "login/loginPage": "redirect:/";
		return new ModelAndView(setViewName);
	}
	
	@GetMapping(value="/register")
	public ModelAndView registerPage(@LoginMember SessionMember member) throws Exception {
		String setViewName = member==null? "login/register": "redirect:/";
		return new ModelAndView(setViewName);
	}
	
	@GetMapping(value="/profile")
	public ModelAndView profilePage(@LoginMember SessionMember member) {
		//String setViewName = member==null? "redirect:/lavender/nidlogin": "login/profilePage";
		if(member!=null && member.getRole() == Role.GUEST)
			return new ModelAndView("login/profilePage");
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/profile/reset")
	public ModelAndView resetProfileImg(@LoginMember SessionMember guest){
		guestJpaService.profileReset(guest.getName());
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/admin")
	public ModelAndView admin() throws Exception {
		ModelAndView modelAndView = new ModelAndView("login/admin");
		return modelAndView;
	}
}
