package com.spring.LAB.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.member.DAO.MemberDAO;
import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.vo.*;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberDAO memberDAO;

	@Override
	public List<MemberVO> listMember() throws DataAccessException {
		List<MemberVO> memberList = memberDAO.listMember();
		return memberList;
	}
	
	/*@Override
	public MemberVO VOMember(String id) throws DataAccessException {	
		memberVO = memberDAO.VOMember(id);
		return memberVO;
	}*/
	
	@Override
	public boolean findIsGuest(GuestRequestDTO guest) throws DataAccessException {
		return Boolean.parseBoolean(memberDAO.findIsGuest(guest));
	}

	public boolean findDuplicateGuest(String id) throws DataAccessException{
		return Boolean.parseBoolean(memberDAO.findDuplicateGuest(id));
	}
	
}
