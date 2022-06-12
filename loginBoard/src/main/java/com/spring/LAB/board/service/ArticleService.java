package com.spring.LAB.board.service;


import com.spring.LAB.board.DTO.article.ArticleListResponseDTO;
import com.spring.LAB.board.domain.PageLinkIndex;

public interface ArticleService {
	public void findArticlePage(PageLinkIndex boardIdx);
	public ArticleListResponseDTO findByArticleNO(long articleNO);
	public int countAllArticle(String id);
	public void deleteArticle(long articleNO);
	}
