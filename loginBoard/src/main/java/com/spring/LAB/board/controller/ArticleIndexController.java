package com.spring.LAB.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.service.ArticleService;
import com.spring.LAB.board.service.ArticlesJpaService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ArticleIndexController {
	final ArticlesJpaService jpaService;
	final ArticleService articleService;
	@GetMapping("/main") 
	public void main() { 
		ModelAndView m = new ModelAndView("/main1");
		List list = new ArrayList();
		list.add(1);
		m.addObject("list",list);
	}
	
	@GetMapping("/imgtest") 
	public String imgtest() {
		return "/board/imgtest";
	}
	
	@GetMapping(value="/mainboard")
	public ModelAndView mainBoardPage(@RequestParam(required = false) String id, 
			 															@RequestParam(required = false) String page) {
			ModelAndView modelAndView = new ModelAndView("/board/mainboard");
			int articlesTotal = articleService.countAllArticle(id);
			PageLinkIndex pageLinkIdx = new PageLinkIndex(page, articlesTotal, id);
			articleService.viewArticlePage(pageLinkIdx);
			modelAndView.addObject("pageLinkIdx", pageLinkIdx);
			return modelAndView;
	}
	@GetMapping(value="/articles/write")
	public String articlesWrite() {
		
		return "board/writePage";
	}
}
