package com.spring.LAB.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.board.DAO.ArticleDAO;
import com.spring.LAB.board.ect.BoardIndex;
import com.spring.LAB.board.vo.ArticleVO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ArticelServiceImpl implements ArticleService{
	@Autowired
	ArticleDAO articleDAO;
	@Override
	public List<ArticleVO> viewAllArticle() {
		List<ArticleVO> articleList = articleDAO.viewAllArticle();
		return articleList;
	}
	@Override
	public void upHits(long articleNO) {
		articleDAO.upHits(articleNO);
	}
	@Override
	public long addArticle(ArticleVO articleVO) {
		articleDAO.addArticle(articleVO);
		return (long) articleVO.getArticleNO();
	}
	@Override
	public ArticleVO viewArticle(long articleNO) {
		ArticleVO articleVO = articleDAO.viewArticle(articleNO);
		return articleVO;
	}
	@Override
	public void modArticle(ArticleVO articleVO) {
		articleDAO.modArticle(articleVO);
	}
	@Override
	public void deleteArticle(long articleNO) {
		articleDAO.deleteArticle(articleNO);
	}
	@Override
	public List<ArticleVO> writedAllArticle(String id) {
		List<ArticleVO> writedArticleList = articleDAO.writedAllArticle(id);
		return writedArticleList;
	}

	@Override
	public List<ArticleVO> viewArticlePage (BoardIndex boardIdx){
		List<ArticleVO> pageArticleList = articleDAO.viewArticlePage(boardIdx);
		return pageArticleList;
	}
	
	@Override
	public int countAllArticle() {
		int articlesTotal = articleDAO.countAllArticle();
		return articlesTotal;
	}
}
