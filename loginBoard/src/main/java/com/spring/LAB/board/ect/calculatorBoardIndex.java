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
	private int tempStartNum;
	private int tempEndNum;
	
	public calculatorBoardIndex(int pageCurrentNum, int articlesTotalNum) {
		this.articlesTotalNum = articlesTotalNum;
		this.pageCurrentNum = pageCurrentNum;                        
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

	private void setPageStartEndNum() {
		int sectionCurrentNum = (pageCurrentNum-1)/10+1;
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
