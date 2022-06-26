package com.spring.LAB.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.service.GuestJpaService;
import com.spring.LAB.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController{
	private final MemberService memberService;
	private final GuestJpaService guestJpaService;
	private final HttpSession session;

	@PostMapping(value="/glogin")
	public boolean login(@RequestBody GuestRequestDTO guest) throws Exception{
		return guestJpaService.findGuest(guest);
	}
	
	@PostMapping(value="/register/save")
	public long register(@RequestBody GuestRequestDTO guest) throws Exception {
		return guestJpaService.save(guest);
	}
	
	@PostMapping(value="/register/check")
	public boolean registerCheck(@RequestBody GuestRequestDTO guest) throws Exception {
		return memberService.findDuplicateGuest(guest.getId());
	}
	
	@PostMapping(value="/guest/delete")
	public void jtest()throws Exception {
		System.out.println("jtest");
	}
	
	@PostMapping(value="/delete")
	public void deleteMember(@LoginMember SessionMember sessionGuest) throws Exception {
		System.out.println("deletes: ");
		guestJpaService.delete(sessionGuest);
		session.removeAttribute("member");
	}
	
	@PostMapping(value="/guest/profile")
	public ModelAndView setProfileImg(@RequestParam(value="profileImg") MultipartFile profileFile)
	 throws Exception {
		
		
		//memberService.profileMember(profileImg, id);
		//memberVO = memberService.VOMember(id);
		//session.setAttribute("memberVO", memberVO);
		//String imgUrl = "/img/profile/"+memberVO.getId()+"/"+memberVO.getProfileImg();
		//System.out.println("profile-"+imgUrl);
		//session.setAttribute("imgUrl", imgUrl);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/main.do");
		return modelAndView;
	}

	
}
