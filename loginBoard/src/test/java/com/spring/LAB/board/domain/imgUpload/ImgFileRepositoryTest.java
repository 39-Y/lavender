package com.spring.LAB.board.domain.imgUpload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.LAB.board.domain.articles.Articles;

@SpringBootTest
public class ImgFileRepositoryTest {
	@Autowired
	ImgFileRepository repository;
	
	@AfterEach
	public void reset() {
		repository.deleteAll();
	}
	@Test
	public void imgInsert() {
		String originalName="testImg";
		String mimeType="img";
		long articleNO = 26;
		byte[]  fileByte= originalName.getBytes();
		repository.save(ImgFiles.builder()
														.originalName(originalName)
														.mimeType(mimeType)
														.fileByte(fileByte)
														.articleNO(articleNO)
														.build());
		
		List<ImgFiles> imFileList = repository.findAll();
		ImgFiles imgFile = imFileList.get(0);
		assertThat(imgFile.getOriginalName()).isEqualTo(originalName);
		assertThat(imgFile.getFileByte()).isEqualTo(fileByte);
		assertThat(imgFile.getMimeType()).isEqualTo(mimeType);
		assertThat(imgFile.getArticleNO()).isEqualTo(articleNO);
		assertThat(imgFile.getImgNO()).isGreaterThan(0);
	}
	
}
