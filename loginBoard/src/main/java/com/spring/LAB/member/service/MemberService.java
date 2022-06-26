package com.spring.LAB.member.service;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.vo.MemberVO;

public interface MemberService {
	public List listMember() throws DataAccessException;
	public boolean findIsGuest(GuestRequestDTO guest) throws DataAccessException;
	//public MemberVO VOMember(String id) throws DataAccessException;
	public boolean findDuplicateGuest(String id) throws DataAccessException;
}
