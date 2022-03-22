package com.spring.LAB.member.service;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.spring.LAB.member.vo.MemberVO;

public interface MemberService {
	public List listMember() throws DataAccessException;
	public boolean isMember(String id, String pw) throws DataAccessException;
	public MemberVO VOMember(String id) throws DataAccessException;
	public void addMember(MemberVO memberVO) throws DataAccessException;
	public void modMember(MemberVO memberVO) throws DataAccessException;
	public String isOverlapped(String id) throws DataAccessException;
	public void deleteMember(String id) throws DataAccessException;
	public void profileMember(String profileImg, String id) throws DataAccessException;
	
}
