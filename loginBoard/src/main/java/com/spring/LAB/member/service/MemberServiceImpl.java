package com.spring.LAB.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.member.DAO.MemberDAO;
import com.spring.LAB.member.vo.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	MemberVO memberVO = new MemberVO();

	@Override
	public List<MemberVO> listMember() throws DataAccessException {
		List<MemberVO> memberList = memberDAO.listMember();
		return memberList;
	}
	
	@Override
	public MemberVO VOMember(String id) throws DataAccessException {	
		memberVO = memberDAO.VOMember(id);
		return memberVO;
	}
	
	@Override
	public boolean isMember(String id, String pw) throws DataAccessException {
		boolean logOn = false;
		String result = memberDAO.isMember(id,pw);
	  logOn = Boolean.valueOf(result);
		return logOn;
	}

	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.addMember(memberVO);
	}
	@Override
	public String isOverlapped(String id) throws DataAccessException{
		String result = memberDAO.isOverlapped(id);
		return result;
	}

	@Override
	public void modMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.modMember(memberVO);
	}

	@Override
	public void deleteMember(String id) throws DataAccessException {
		memberDAO.deleteMember(id);
	}

	@Override
	public void profileMember(String profileImg, String id) throws DataAccessException {
		memberDAO.profileMember(profileImg, id);
	}
	
	
}
