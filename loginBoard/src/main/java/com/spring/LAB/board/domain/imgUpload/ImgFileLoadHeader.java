package com.spring.LAB.board.domain.imgUpload;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.LAB.board.DTO.imgFile.ImgFileDTO;
import com.spring.LAB.member.DTO.GuestProfileResponseDTO;


public class ImgFileLoadHeader {

	public ResponseEntity<byte[]> load(ImgFileDTO imgDTO){
		HttpHeaders header = new HttpHeaders();
		byte[] fileByte = imgDTO.getFileByte();
		header.add("Content-Type", imgDTO.getMimeType());
		header.add("Content-Length", String.valueOf(fileByte.length));
		return new ResponseEntity<byte[]>(fileByte, header, HttpStatus.OK);
	}
	
	public ResponseEntity<byte[]> load(GuestProfileResponseDTO guestProfileDTO){
		HttpHeaders header = new HttpHeaders();
		byte[] fileByte = guestProfileDTO.getFileByte();
		header.add("Content-Type", guestProfileDTO.getMimeType());
		header.add("Content-Length", String.valueOf(fileByte.length));
		return new ResponseEntity<byte[]>(fileByte, header, HttpStatus.OK);
	}

}
