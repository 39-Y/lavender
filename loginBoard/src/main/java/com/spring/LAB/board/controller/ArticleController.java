package com.spring.LAB.board.controller;
import com.spring.LAB.board.DTO.article.ArticleUpdateRequestDTO;
import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.config.auth.dto.SessionMember;

public interface ArticleController {
	public long writeArticle(ArticleWriteRequestDto article);
	public long updateArticle(ArticleUpdateRequestDTO article);
	public void deleteArticle(long artricleNO);
}
