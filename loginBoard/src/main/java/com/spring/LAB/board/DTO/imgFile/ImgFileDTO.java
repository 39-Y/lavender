package com.spring.LAB.board.DTO.imgFile;

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
	
}
