package com.spring.LAB.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.domain.imgUpload.RequestImgFileImplement;
import com.spring.LAB.board.domain.imgUpload.ImgFileLoadHeader;
import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;
import com.spring.LAB.member.DTO.GuestProfileRequestDTO;
import com.spring.LAB.member.DTO.GuestProfileResponseDTO;
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
	
	@PostMapping(value="/delete")
	public void deleteMember(@LoginMember SessionMember sessionGuest) throws Exception {
		guestJpaService.delete(sessionGuest.getName());
		session.removeAttribute("member");
	}
	
	@PostMapping(value="/profile")
	public ModelAndView setProfileImg(@RequestParam(value="profileImg") MultipartFile profileFile,
																		@LoginMember SessionMember guest) throws Exception {
		RequestImgFileImplement requestImgImpl = new RequestImgFileImplement(profileFile);
		GuestProfileRequestDTO guestProfileDTO = requestImgImpl.getGuestProfileRequestDTO();
		guestJpaService.profileUpdate(guest.getName(), guestProfileDTO);
		
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/profile/img/{guestName}")
	public ResponseEntity viewProfileImg(@PathVariable String guestName){
		GuestProfileResponseDTO guestProfileResponseDTO = guestJpaService.findProfileByName(guestName);
		ImgFileLoadHeader imgFileLoadHeader = new ImgFileLoadHeader();
		return imgFileLoadHeader.load(guestProfileResponseDTO);
	}
	
}
