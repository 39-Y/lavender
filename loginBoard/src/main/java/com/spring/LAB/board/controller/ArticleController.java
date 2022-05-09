package com.spring.LAB.board.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.board.vo.ArticleVO;

public interface ArticleController {
	public ModelAndView viewArticle(long artricleNO);
	public long writeArticle(HttpServletRequest request, ArticleWriteRequestDto article);
	public ModelAndView modArticle(HttpServletRequest request, ArticleVO articleVO);
	public ModelAndView deleteArticle(HttpServletRequest request,long artricleNO);
}
