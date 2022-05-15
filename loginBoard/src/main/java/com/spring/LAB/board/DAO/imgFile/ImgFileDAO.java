package com.spring.LAB.board.DAO.imgFile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.LAB.board.DTO.imgFile.ImgFileListResponseDTO;

@Mapper
public interface ImgFileDAO {
	//public ImgFileListResponseDTO findFileName(String fileName);
	public Map findByFileName(String fileName);
	public List<ImgFileListResponseDTO> findByArticleNO(Long articleNO);
	public int countImgFiles(Long articleNO);
}
