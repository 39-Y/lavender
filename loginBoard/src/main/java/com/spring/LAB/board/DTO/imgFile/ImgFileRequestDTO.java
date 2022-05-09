package com.spring.LAB.board.DTO.imgFile;

import com.spring.LAB.board.domain.imgUpload.ImgFiles;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ImgFileRequestDTO extends ImgFileDTO{
	private String originalName;
	private String fileName;
	private String mimeType;
	private byte[] fileByte;
	private long articleNO;
	
	public void setArticleNO(long articleNO) {
		this.articleNO = articleNO;
	}
	
	public ImgFiles toEntity() {
		return ImgFiles.builder()
									 .originalName(originalName)
									 .fileName(fileName)
									 .mimeType(mimeType)
									 .fileByte(fileByte)
									 .articleNO(articleNO)
									 .build();
									 
	}
}
