package com.spring.LAB.board.service.imgFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.board.DAO.imgFile.ImgFileDAO;
import com.spring.LAB.board.DTO.imgFile.ImgFileListResponseDTO;
import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgFileService {
	final ImgFileDAO imgFileDAO;
	
	public ImgFileListResponseDTO findByFileName(String fileName) throws SQLException {
		Map result = imgFileDAO.findByFileName(fileName);
		return new ImgFileListResponseDTO(result);
	}
	
	public List<ImgFileListResponseDTO> findByArticleNO(Long articleNO){
		return imgFileDAO.findByArticleNO(articleNO);
	}
	
	public int countImgFiles(Long articleNO) {
		return imgFileDAO.countImgFiles(articleNO);
	}
	
}
