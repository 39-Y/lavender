package com.spring.LAB.board.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.LAB.board.DTO.ArticleWriteRequestDto;
import com.spring.LAB.board.domain.articles.ArticleRepository;
import com.spring.LAB.board.domain.articles.Articles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class articleControllerTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private ArticleRepository repository;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@AfterEach
	public void reset() {
		//repository.deleteAll();
	}
	
	@Test
	public void saveArticles() {
		String title = "titleForTest";
		String content = "contentForTest";
		String id = "idForTest";
		LocalDateTime now = LocalDateTime.of(2022, 3,4,0,0,0);
		ArticleWriteRequestDto articleDto = ArticleWriteRequestDto
																							.builder()
																							.title(title)
																							.content(content)
																							.id(id)
																							.build();
		
		String url = "http://localhost:"+port+"/lavender/write";
		
		ResponseEntity<Long> responseEntity = 
													restTemplate.postForEntity(url, articleDto, Long.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0);
		
		List<Articles> all = repository.findAll();
		assertThat(all.get(1).getTitle()).isEqualTo(title);
		assertThat(all.get(1).getContent()).isEqualTo(content);
		assertThat(all.get(1).getId()).isEqualTo(id);
		assertThat(all.get(1).getParentNO()).isEqualTo(0);
		System.out.println("<<<<<<<<<<<"+all.get(0).getCreateDate()+", "+ all.get(0).getModifiedDate());
		assertThat(all.get(1).getCreateDate()).isAfter(now);
		assertThat(all.get(1).getModifiedDate().isAfter(now));
	}
	
}
