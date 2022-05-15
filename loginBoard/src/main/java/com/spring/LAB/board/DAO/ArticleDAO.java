package com.spring.LAB.board.DAO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.spring.LAB.board.DTO.article.ArticleListResponseDTO;
import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.vo.ArticleVO;

@Mapper
public interface ArticleDAO {
	public List<ArticleListResponseDTO> findArticlePage(PageLinkIndex boardIdx) throws DataAccessException;
	public ArticleListResponseDTO findByArticleNO(long articleNO) throws DataAccessException;
	public int countAllArticle(String id) throws DataAccessException;
	public void upHits(long articleNO) throws DataAccessException;
	public void deleteArticle(long articleNO) throws DataAccessException;
	
	
}
