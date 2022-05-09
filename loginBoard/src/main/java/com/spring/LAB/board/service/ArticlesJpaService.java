package com.spring.LAB.board.service;

import org.springframework.stereotype.Service;

import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.board.domain.articles.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticlesJpaService {
	final ArticleRepository repository;
	
	public Long save(ArticleWriteRequestDto articleDto) {
		return repository.save(articleDto.toEntity()).getArticleNO();
	}
}
