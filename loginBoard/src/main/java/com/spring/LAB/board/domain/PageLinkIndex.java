package com.spring.LAB.board.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.LAB.board.DTO.article.ArticleListResponseDTO;

public class PageLinkIndex {
	private int pageCurrentNum = 1;
	private int pageStartNum;
	private int pageEndNum;
	private int sectionCurrentNum;
	private int tempStartNum;
	private int tempEndNum;
	public int articlesTotalNum;
	public List<Integer> pageIdxNumsList= new ArrayList<Integer>();
	public List<ArticleListResponseDTO> articlesList;
	public int articleStartNum;
	public int articleEndNum;
	public int prePage;
	public int nextPage;
	public String id;
	
	public PageLinkIndex(String currentPage, int articlesTotalNum, String id) {
		if(currentPage != null)	
			this.pageCurrentNum = Integer.parseInt(currentPage);
		this.articlesTotalNum = articlesTotalNum;
		this.id = id;
		sectionCurrentNum = (pageCurrentNum-1)/10+1;
		setPageIdxNumsList();
		setArticleNums();
		setNextButton();
		setPreButton();
	}
	
	private void setNextButton() {
		int sectionTotalNum = (articlesTotalNum-1)/100+1;
		if(sectionCurrentNum < sectionTotalNum) 
			nextPage = pageEndNum+1;
	}
	
	private void setPreButton() {
		if(sectionCurrentNum > 1) 
			prePage = pageStartNum-1;
	}
	
	public void setArticlesList(List<ArticleListResponseDTO> articlesList) {
		for(ArticleListResponseDTO article:articlesList) {
			article.parseModifiedDate();
		}
		this.articlesList = articlesList;
	}
	
	private void setPageIdxNumsList() {
		int pagesTotalNum = (articlesTotalNum-1)/10+1;
		calculateStartEndPoint(sectionCurrentNum, pagesTotalNum);
		pageStartNum = tempStartNum;
		pageEndNum = tempEndNum;
		for(int i=pageStartNum; i<pageEndNum+1; i++) {
			pageIdxNumsList.add(i);
		}
	}
	
	private void setArticleNums() {
		calculateStartEndPoint(pageCurrentNum, articlesTotalNum);
		articleStartNum = tempStartNum;
		articleEndNum = tempEndNum;
	}

	private void calculateStartEndPoint(int current, int total) {
		tempStartNum = (current-1)*10+1;
		tempEndNum = current*10;
		if(tempEndNum > total)
			tempEndNum = total;
		if(tempStartNum > tempEndNum)
			tempStartNum = tempEndNum;
	}
	
}
