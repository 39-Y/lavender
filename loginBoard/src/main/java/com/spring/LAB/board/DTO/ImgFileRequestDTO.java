package com.spring.LAB.board.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImgFileRequestDTO {
	private long imgNO;
	private String originalName;
	private String fileName;
	private String mimeType;
	private byte[] fileByte;
	
	@Builder
	public ImgFileRequestDTO(String originalName,
													 String fileName,
													 String mimeType, 
													 byte[] fileByte) {
		this.originalName = originalName;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
	}
}
