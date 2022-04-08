package com.spring.LAB.board.ect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class calculatorBoardIndex {
	private int articlesTotalNum;
	private int pageCurrentNum;
	public int articleStartNum;
	public int articleEndNum;
	private int pageStartNum;
	private int pageEndNum;
	private int sectionCurrentNum;
	private int tempStartNum;
	private int tempEndNum;
	
	public calculatorBoardIndex(int pageCurrentNum, int articlesTotalNum) {
		this.articlesTotalNum = articlesTotalNum;
		this.pageCurrentNum = pageCurrentNum;
		sectionCurrentNum = (pageCurrentNum-1)/10+1;
		setPageStartEndNum();
		setArticleStartEndNum();
	}
	
	public List<Integer> getPageNumList(){
		List<Integer> pageIdxNums= new ArrayList<Integer>();
		for(int i=pageStartNum; i<pageEndNum+1; i++) {
			pageIdxNums.add(i);
		}
		return pageIdxNums;
	}
	
	public int getNextButton() {
		int sectionTotalNum = (articlesTotalNum-1)/100+1;
		int nextPage=0;
		if(sectionCurrentNum < sectionTotalNum) 
			nextPage = pageEndNum+1;
		return nextPage;
	}
	
	public int getPreButton() {
		int prePage = 0;
		if(sectionCurrentNum > 1) 
			prePage = pageStartNum-1;
		return prePage;
	}
	
	private void setPageStartEndNum() {
		int pagesTotalNum = (articlesTotalNum-1)/10+1;
		calculateStartEndPoint(sectionCurrentNum, pagesTotalNum);
		pageStartNum = tempStartNum;
		pageEndNum = tempEndNum;
	}
	
	private void setArticleStartEndNum() {
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
