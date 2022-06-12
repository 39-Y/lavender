package com.spring.LAB.config.auth.dto;

import java.io.Serializable;

import com.spring.LAB.member.domain.member.Member;

import lombok.Getter;

@Getter
public class SessionMember implements Serializable{
	private String name;
	private String email;
	private String picture;
	
	public SessionMember(Member member) {
		this.name = member.getName();
		this.email = member.getEmail();
		this.picture = member.getPicture();
	}
}
