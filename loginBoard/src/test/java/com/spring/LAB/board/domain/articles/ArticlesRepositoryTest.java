package com.spring.LAB.board.domain.articles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticlesRepositoryTest {
	@Autowired
	ArticleRepository repository;
	
	@AfterEach
	public void reset() {
		repository.deleteAll();
	}
	
	@Test
	public void insertAndSelectTest() {
		String title="테스트 글입니다.";
		String content="테스트 글의 본문";
		String id = "hong";
		
		repository.save(Articles.builder()
														.title(title)
														.content(content)
														.id(id)
														.build());
		
		List<Articles> articleList = repository.findAll();
		
		Articles article = articleList.get(0);
		assertThat(article.getTitle()).isEqualTo(title);
		assertThat(article.getContent()).isEqualTo(content);
		assertThat(article.getId()).isEqualTo(id);
		assertThat(article.getHits()).isEqualTo(0);
	}
	
}
