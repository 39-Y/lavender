package com.spring.LAB.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.LAB.member.vo.MemberVO;

@RestController
public class hiController {

	@GetMapping(value="/test/hi")
	public String hi1() {
		String hi = "hi";
		return hi;
	}
	
	@GetMapping(value="/test/hi2")
	public MemberVO hi2(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		return vo;
	}
}
