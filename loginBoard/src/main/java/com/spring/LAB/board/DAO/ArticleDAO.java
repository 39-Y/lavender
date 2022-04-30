package com.spring.LAB.board.DAO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.spring.LAB.board.DTO.ArticleListResponseDTO;
import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.vo.ArticleVO;

@Mapper
public interface ArticleDAO {
	public List<ArticleVO> viewAllArticle() throws DataAccessException;
	public void upHits(long articleNO) throws DataAccessException;
	public void addArticle(ArticleVO articleVO) throws DataAccessException;
	public void modArticle(ArticleVO articleVO) throws DataAccessException;
	public ArticleVO viewArticle(long articleNO) throws DataAccessException;
	public void deleteArticle(long articleNO) throws DataAccessException;
	public List<ArticleVO> writedAllArticle(String id) throws DataAccessException;
	public List<ArticleListResponseDTO> viewArticlePage(PageLinkIndex boardIdx) throws DataAccessException;
	public int countAllArticle(String id) throws DataAccessException;
}
