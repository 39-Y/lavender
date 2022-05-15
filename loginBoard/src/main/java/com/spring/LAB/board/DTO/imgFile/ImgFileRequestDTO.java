package com.spring.LAB.board.DTO.imgFile;

import com.spring.LAB.board.domain.imgUpload.ImgFiles;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
public class ImgFileRequestDTO extends ImgFileDTO{
	private String originalName;
	private String fileName;
	private String mimeType;
	private byte[] fileByte;
	private long articleNO;
	
	@Builder
	public ImgFileRequestDTO(String fileName,
										String originalName,
										String mimeType, 
										byte[] fileByte,
										long articleNO) {
		this.fileName = fileName;
		this.originalName = originalName;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
		this.articleNO = articleNO;
	}
	
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
