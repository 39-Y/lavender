package com.spring.LAB.member.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
		private String id;
		private String pw;
		private String name;
		private String email;
		private String profileImg;
		private Date joinDate;
}
