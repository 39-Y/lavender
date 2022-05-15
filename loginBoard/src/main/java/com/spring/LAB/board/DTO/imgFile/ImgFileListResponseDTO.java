package com.spring.LAB.board.DTO.imgFile;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class ImgFileListResponseDTO extends ImgFileDTO{
	private long imgNO;
	private String originalName;
	private String fileName;
	private String mimeType;
	private byte[] fileByte;
	private long articleNO;
	
	@Builder
	public ImgFileListResponseDTO(String fileName,
										String originalName,
										String mimeType, 
										byte[] fileByte,
										long articleNO) throws SQLException {
		this.fileName = fileName;
		this.originalName = originalName;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
		this.articleNO = articleNO;
	}
	
	public ImgFileListResponseDTO(Map result) throws SQLException {
		this.fileName=(String) result.get("FILENAME");
		this.originalName=(String) result.get("ORIGINALNAME");
		this.mimeType=(String) result.get("MIMETYPE");
		this.fileByte = blobToByteArray((Blob) result.get("FILEBYTE"));
		this.articleNO=bigDecimalToLong((BigDecimal) result.get("ARTICLENO"));
	}

	private byte[] blobToByteArray(Blob fileBlob) throws SQLException {
		int length = (int)fileBlob.length();
		byte[] byteArrary = fileBlob.getBytes(1, length);
		fileBlob.free();
		return byteArrary;
	}
	
	private long bigDecimalToLong(BigDecimal decimal) {
		return decimal.longValue();
	}
}
