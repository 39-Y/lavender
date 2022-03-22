package com.spring.LAB.member.DAO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import com.spring.LAB.member.vo.MemberVO;

@Mapper
public interface MemberDAO {
	public List<MemberVO> listMember() throws DataAccessException;
	public MemberVO VOMember(String id) throws DataAccessException;
	public String isMember(String id, String pw) throws DataAccessException;
	public void addMember(MemberVO memberVO) throws DataAccessException;
	public String isOverlapped(String id) throws DataAccessException;
	public void modMember(MemberVO memberVO) throws DataAccessException;
	public void deleteMember(String id) throws DataAccessException;
	public void profileMember(String profileImg, String id) throws DataAccessException;
}
