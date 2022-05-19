package com.spring.LAB.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.board.DTO.article.ArticleUpdateRequestDTO;
import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.board.domain.articles.ArticleRepository;
import com.spring.LAB.board.domain.articles.Articles;

import lombok.RequiredArgsConstructor;
@Transactional
@Service
@RequiredArgsConstructor
public class ArticlesJpaService {
	final ArticleRepository repository;
	
	public Long save(ArticleWriteRequestDto articleDto) {
		return repository.save(articleDto.toEntity()).getArticleNO();
	}
	private Articles findById(Long articleNO) {
		return repository.findById(articleNO)
										 .orElseThrow(
												 ()-> new IllegalArgumentException
												 ("해당 게시글이 없습니다. id="+articleNO));
	}
	
	public Long update(ArticleUpdateRequestDTO articleDto) {
		long articleNO = articleDto.getArticleNO();
		Articles articles = findById(articleNO);
		articles.update(articleDto.getTitle(), articleDto.getContent());
		return articleNO;
	}
	
	public void delete(Long articleNO) {
		Articles articles = findById(articleNO);
		repository.delete(articles);
	}
}
