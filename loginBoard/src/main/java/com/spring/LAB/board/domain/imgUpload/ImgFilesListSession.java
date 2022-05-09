package com.spring.LAB.board.domain.imgUpload;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;

public class ImgFilesListSession {
	private HttpSession session;
	private String content;
	private List<ImgFileRequestDTO> existImgFileList;
	
	public ImgFilesListSession(HttpServletRequest request) {
		session = request.getSession();
	}
	
	public List<ImgFileRequestDTO> getAttribute() {
		return (List<ImgFileRequestDTO>) session.getAttribute("imgFilesList");
	}
	
	public void initList() {
		session.setAttribute("imgFilesList", new ArrayList<ImgFileRequestDTO>());
	}
	
	public void setAttributeList(List<ImgFileRequestDTO> list) {
		session.setAttribute("imgFilesList", list);
	}
	
	public void deleteList() {
		session.removeAttribute("imgFilesList");
	}
	
	public List<ImgFileRequestDTO> findImgFiles(String content){
		return findImgFiles(content, 0L);
	}
	
	public List<ImgFileRequestDTO> findImgFiles(String content, Long articleNO){
		List<ImgFileRequestDTO> imgFilesList = getAttribute();
		if(imgFilesList == null || imgFilesList.size() == 0) 
			return null;
		this.content = content;
		for(ImgFileRequestDTO img: imgFilesList) {
			if(articleNO>0)
				setExistImgFileList(img, articleNO);
			else
				setExistImgFileList(img);
		}
		return existImgFileList;
	}
	
	private void setExistImgFileList(ImgFileRequestDTO img, Long articleNO) {
		if(content.indexOf("<img src=\"/articles/img/"+img.getFileName())> -1 ) {
			existImgFileList = new ArrayList<ImgFileRequestDTO>();
			img.setArticleNO(articleNO);
			existImgFileList.add(img);
		}
	}
	
	private void setExistImgFileList(ImgFileRequestDTO img) {
		if(content.indexOf(img.getFileName())> -1) {
			existImgFileList = new ArrayList<ImgFileRequestDTO>();
			existImgFileList.add(img);
		}
	}
}
