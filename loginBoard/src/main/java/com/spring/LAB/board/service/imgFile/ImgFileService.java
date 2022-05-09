package com.spring.LAB.board.service.imgFile;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.LAB.board.DAO.ImgFileDAO;
import com.spring.LAB.board.DTO.imgFile.ImgFileListResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImgFileService {
	final ImgFileDAO imgFileDAO;
	
	public ImgFileListResponseDTO findFileName(String fileName) {
		return imgFileDAO.findFileName(fileName);
	}
	
	public List<ImgFileListResponseDTO> findArticleNO(Long articleNO){
		return imgFileDAO.findArticleNO(articleNO);
	}
}
