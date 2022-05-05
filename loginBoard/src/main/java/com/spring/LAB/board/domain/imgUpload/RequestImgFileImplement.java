package com.spring.LAB.board.domain.imgUpload;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.LAB.board.DTO.ImgFileRequestDTO;

public class RequestImgFileImplement {
	private ImgFileRequestDTO imgFileDTO;
	private HttpSession session;
	private List<ImgFileRequestDTO> imgFilesList;
	private String fileName;
	
	public RequestImgFileImplement(HttpServletRequest request) throws IOException {
		session = request.getSession();
		String originalName = request.getHeader("file-Name");
		fileName = setFileName();
		String mimeType=request.getHeader("file-Type");
		InputStream is = request.getInputStream();
		byte[] fileByte = is.readAllBytes();
		imgFileDTO = ImgFileRequestDTO.builder()
																	.originalName(originalName)
																	.fileName(fileName)
																	.mimeType(mimeType)
																	.fileByte(fileByte)
																	.build();
	}
	
	public void loadToSession() {
		imgFilesList = (List<ImgFileRequestDTO>) session.getAttribute("imgFilesList");
		if(imgFilesList == null) { 
			imgFilesList =  new ArrayList<ImgFileRequestDTO>();
		}
		imgFilesList.add(imgFileDTO);
		session.setAttribute("imgFilesList", imgFilesList);	
	}
	
	public String getFileName() {
		return fileName;
	}

	private String setFileName() {
		LocalDateTime currentDate = LocalDateTime.now();
		String fileName = currentDate.format(DateTimeFormatter.ofPattern("yyyymmddHHMMssSSS"));
		return fileName;
	}
}
