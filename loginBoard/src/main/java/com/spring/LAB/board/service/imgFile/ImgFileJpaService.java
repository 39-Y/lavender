package com.spring.LAB.board.service.imgFile;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.LAB.board.DTO.article.ArticleUpdateRequestDTO;
import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;
import com.spring.LAB.board.domain.imgUpload.ImgFileRepository;
import com.spring.LAB.board.domain.imgUpload.ImgFiles;

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
	public void deleteAll(List<Long> notExistFileList) {
		for(Long imgNO:notExistFileList) {
			ImgFiles imgFile = repository.findById(imgNO)
																	 .orElseThrow(
																			 () -> new IllegalArgumentException(
																					 	"해당 이미지가 없습니다. imgNO: "+imgNO));
			repository.delete(imgFile);
		}
	}
	
	public void deleteNotExistImgFiles(ArticleUpdateRequestDTO article) {
		List<ImgFiles> oldImgFiles = repository.findAllByArticleNO(article.getArticleNO());
		oldImgFiles.stream()
							 .filter(img -> article.getContent().indexOf(img.getFileName())<0)
							 .forEach(img -> repository.delete(img));
	}

}
