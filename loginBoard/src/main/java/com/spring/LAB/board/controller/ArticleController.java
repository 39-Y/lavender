package com.spring.LAB.board.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import com.spring.LAB.board.vo.ArticleVO;

public interface ArticleController {
	public ModelAndView mainBoardPage(HttpServletRequest request);
	public ModelAndView viewArticle(long artricleNO);
	public ModelAndView writeArticle(HttpServletRequest request, ArticleVO articleVO);
	public ModelAndView modArticle(HttpServletRequest request, ArticleVO articleVO);
	public ModelAndView deleteArticle(HttpServletRequest request,long artricleNO);
}
