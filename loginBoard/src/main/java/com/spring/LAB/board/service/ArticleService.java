package com.spring.LAB.board.service;

import java.util.List;

import com.spring.LAB.board.vo.ArticleVO;

public interface ArticleService {
	public List<ArticleVO> viewAllArticle();
	public void upHits(long articleNO); 
	public long addArticle (ArticleVO articleVO);
	public ArticleVO viewArticle(long articleNO);
	public void modArticle(ArticleVO articleVO);
	public void deleteArticle(long articleNO);
	public List<ArticleVO> writedAllArticle(String id);
	public List<ArticleVO> viewArticlePage(int startIdx, int endIdx);
	public int countAllArticle();
	}
