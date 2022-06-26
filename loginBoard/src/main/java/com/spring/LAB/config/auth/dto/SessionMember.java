package com.spring.LAB.config.auth.dto;

import java.io.Serializable;

import com.spring.LAB.member.domain.guest.Guest;
import com.spring.LAB.member.domain.member.Member;
import com.spring.LAB.member.domain.member.Role;

import lombok.Getter;

@Getter
public class SessionMember implements Serializable{
	private String name;
	private String email;
	private String picture;
	private Role role;
	
	public SessionMember(Member member) {
		this.name = member.getName();
		this.email = member.getEmail();
		this.picture = member.getPicture();
		this.role = member.getRole();
	}
	
	public SessionMember(Guest guest) {
		this.name = guest.getId();
		this.email = guest.getId();
		this.picture = guest.getProfile();
		this.role = guest.getRole();
	}
}
