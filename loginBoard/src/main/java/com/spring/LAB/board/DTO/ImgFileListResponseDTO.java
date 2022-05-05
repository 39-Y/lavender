package com.spring.LAB.board.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImgFileListResponseDTO {
	private long imgNO;
	private String originalName;
	private String mimeType;
	private byte[] fileByte;
	
	public ImgFileListResponseDTO(long imgNO,
																String originalName, 
																String mimeType, 
																byte[] fileByte) {
		this.imgNO = imgNO;
		this.originalName = originalName;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
	}
}
