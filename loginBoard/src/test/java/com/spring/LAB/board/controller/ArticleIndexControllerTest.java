package com.spring.LAB.board.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleIndexControllerTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private ArticleRepository repository;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void mainBoardPage() {
		//미리 data 넣어놓고 테스트 진행할 것
		String url = "http://localhost:"+port+"/lavender/mainboard";
		String body = this.restTemplate.getForObject(url, String.class);
		assertThat(body).contains("titleForTest");
		//ResponseEntity<Long> responseEntity = restTemplate
			//	restTemplate.;
	}
	
}
