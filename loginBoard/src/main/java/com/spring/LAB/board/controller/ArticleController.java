package com.spring.LAB.board.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.DTO.article.ArticleUpdateRequestDTO;
import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.board.vo.ArticleVO;

public interface ArticleController {
	public long writeArticle(ArticleWriteRequestDto article);
	public long updateArticle(ArticleUpdateRequestDTO article);
	public ModelAndView deleteArticle(HttpServletRequest request,long artricleNO);
}
