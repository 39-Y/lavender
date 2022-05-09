package com.spring.LAB.board.DTO.imgFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ImgFileDTO {
	private long imgNO;
	private String originalName;
	private String fileName;
	private String mimeType;
	private byte[] fileByte;
	private long articleNO;
	
	@Builder
	public ImgFileDTO(String fileName,
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
}
