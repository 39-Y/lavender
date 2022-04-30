package com.spring.LAB.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String main(@RequestParam(required = false) String id, 
										 @RequestParam(required = false) String page){
		System.out.println("id: "+id);
		System.out.println("page: "+page);
		return "main";
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
