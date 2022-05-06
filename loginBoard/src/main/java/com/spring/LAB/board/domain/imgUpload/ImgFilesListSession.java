package com.spring.LAB.board.domain.imgUpload;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.LAB.board.DTO.ImgFileRequestDTO;

public class ImgFilesListSession {
	private List<ImgFileRequestDTO> imgFilesList;
	private HttpSession session;
	private long articleNO;
	
	public ImgFilesListSession(HttpServletRequest request) {
		session = request.getSession();
		imgFilesList = (List<ImgFileRequestDTO>) session.getAttribute("imgFilesList");
	}
	
	public List<ImgFileRequestDTO> getImgFilesList() {
		return imgFilesList;
	}
	
	public void deleteList() {
		session.removeAttribute("imgFilesList");
	}
	
	public List<ImgFileRequestDTO> existImgFiles(String content){
		return existImgFiles(content,0L);
	}
	
	public List<ImgFileRequestDTO> existImgFiles(String content, Long articleNO){
		this.articleNO = articleNO;
		List<ImgFileRequestDTO> existImgFile = new ArrayList<ImgFileRequestDTO>();
		if(imgFilesList == null) return null;
		for(ImgFileRequestDTO img: imgFilesList) {
			if(content.indexOf(img.getFileName())>0 ) {
				setArticleNO(img);
				existImgFile.add(img);
			}
		}
		return existImgFile;
	}
	private void setArticleNO(ImgFileRequestDTO img) {
		if(articleNO>0) 
			img.setArticleNO(articleNO);
	}
	
}
