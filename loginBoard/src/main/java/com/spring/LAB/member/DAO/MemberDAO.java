package com.spring.LAB.member.DAO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.spring.LAB.member.DTO.GuestRequestDTO;
import com.spring.LAB.member.vo.MemberVO;

@Mapper
public interface MemberDAO {
	public List<MemberVO> listMember() throws DataAccessException;
	public String findIsGuest(GuestRequestDTO guest) throws DataAccessException;
	public String findDuplicateGuest(String id) throws DataAccessException;
}
