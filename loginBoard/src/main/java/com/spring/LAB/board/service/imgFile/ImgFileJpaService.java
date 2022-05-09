package com.spring.LAB.board.service.imgFile;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.LAB.board.DTO.imgFile.ImgFileListResponseDTO;
import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;
import com.spring.LAB.board.domain.imgUpload.ImgFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImgFileJpaService {
	final ImgFileRepository repository;
	
	public String save(ImgFileRequestDTO imgFile) {
		return repository.save(imgFile.toEntity()).getFileName();
	}
	public void saveAll(List<ImgFileRequestDTO> imgFileList) {
		repository.saveAll(imgFileList.stream()
																	.map(ImgFileRequestDTO::toEntity)
																	.collect(Collectors.toList()));
	}

}
