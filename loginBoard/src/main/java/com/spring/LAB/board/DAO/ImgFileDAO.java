package com.spring.LAB.board.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.LAB.board.DTO.imgFile.ImgFileListResponseDTO;

@Mapper
public interface ImgFileDAO {
	public ImgFileListResponseDTO findFileName(String fileName);
	public List<ImgFileListResponseDTO> findArticleNO(Long articleNO);

}
