package com.spring.LAB.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.LAB.board.DAO.ArticleDAO;
import com.spring.LAB.board.DTO.article.ArticleListResponseDTO;
import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.vo.ArticleVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class ArticelServiceImpl implements ArticleService{
	final ArticleDAO articleDAO;
	
	@Override
	public void findArticlePage (PageLinkIndex boardIdx){
		List<ArticleListResponseDTO> pageArticleList = articleDAO.findArticlePage(boardIdx);
		boardIdx.setArticlesList(pageArticleList);
	}
	
	@Override
	public ArticleListResponseDTO findByArticleNO(long articleNO) {
		articleDAO.upHits(articleNO);
		ArticleListResponseDTO article= articleDAO.findByArticleNO(articleNO);
		article.parseModifiedDate();
		return article;
	}
	
	@Override
	public void deleteArticle(long articleNO) {
		articleDAO.deleteArticle(articleNO);
	}
	
	
	@Override
	public int countAllArticle(String id) {
		int articlesTotal = articleDAO.countAllArticle(id);
		return articlesTotal;
	}

}
