package com.spring.LAB.board.domain.imgUpload;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.LAB.board.DTO.imgFile.ImgFileDTO;
import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class RequestImgFileImplement {
	private ImgFileRequestDTO imgFileDTO;
	private List<ImgFileRequestDTO> imgFilesList;
	private String fileName;
	private ImgFilesListSession imgListSession;
	
	public RequestImgFileImplement(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		imgListSession = new ImgFilesListSession(session);
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
	
	public void imgLoadToSession() throws InterruptedException {
		imgFilesList = imgListSession.getAttribute();
		imgFilesList.add(imgFileDTO);
		imgListSession.setAttributeList(imgFilesList);
	}
	
	public String getFileName() {
		return fileName;
	}

	private String setFileName() {
		LocalDateTime currentDate = LocalDateTime.now();
		String fileName = currentDate.format(DateTimeFormatter.ofPattern("yyyymmddHHMMssSSS"))
				+String.valueOf((int)(Math.random()*100000));
		return fileName;
	}
}
