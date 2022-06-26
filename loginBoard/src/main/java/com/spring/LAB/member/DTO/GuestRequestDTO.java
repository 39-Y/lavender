package com.spring.LAB.member.DTO;

import com.spring.LAB.member.domain.guest.Guest;
import com.spring.LAB.member.domain.member.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestRequestDTO {
	private String id;
	private String pwd;
	private String profile; 
	
	@Builder
	public GuestRequestDTO(String id,
										String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public Guest toEntity() {
		return Guest.builder()
								.id(id)
								.pwd(pwd)
								.profile(profile)
								.build();
	}
}
