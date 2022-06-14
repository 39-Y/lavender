package com.spring.LAB.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.DTO.article.ArticleListResponseDTO;
import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.domain.imgUpload.ImgFilesListSession;
import com.spring.LAB.board.service.ArticleService;
import com.spring.LAB.board.service.ArticlesJpaService;
import com.spring.LAB.config.auth.LoginMember;
import com.spring.LAB.config.auth.dto.SessionMember;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ArticleIndexController {
	final ArticlesJpaService jpaService;
	final ArticleService articleService;
	final HttpSession session;
	
	@GetMapping(value="/")
	public String mainPage(){
		return "/main";
	}
	
	@GetMapping(value="/mainboard/{page}")
	public ModelAndView mainBoardPage(@PathVariable String page,
																		@RequestParam(required = false) String id){
			ModelAndView modelAndView = new ModelAndView("board/mainboard");
			int articlesTotal = articleService.countAllArticle(id);
			PageLinkIndex pageLinkIdx = new PageLinkIndex(page, articlesTotal, id);
			articleService.findArticlePage(pageLinkIdx);
			modelAndView.addObject("pageLinkIdx", pageLinkIdx);
			return modelAndView;
	}
	
		@GetMapping(value="/article/{articleNO}")
	public ModelAndView articlePage(@PathVariable long articleNO,HttpServletRequest request,
																	@LoginMember SessionMember member) {
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		imgFileListSession.deleteList();
		ModelAndView modelAndView = new ModelAndView("/board/articlePage");
		ArticleListResponseDTO article = articleService.findByArticleNO(articleNO);
		modelAndView.addObject("article", article);
		boolean isAuthor = member==null? false : article.getId().equals(member.getEmail());
		modelAndView.addObject("isAuthor", isAuthor);
		return modelAndView;
	}
		
	@GetMapping(value="/articles/write")
	public ModelAndView articlesWrite(@RequestParam(required = false) String parentNO,
																		HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView("board/writePage");
			ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
			imgFileListSession.initList();
			modelAndView.addObject("parentNO", parentNO);
			return modelAndView;
	}
	
	@GetMapping(value="/articles/update/{articleNO}")
	public ModelAndView modArticlePage(@PathVariable(value="articleNO")long articleNO,
																		 @LoginMember SessionMember member) {
		ModelAndView modelAndView = new ModelAndView("board/updatePage");
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		imgFileListSession.initList();
		ArticleListResponseDTO article = articleService.findByArticleNO(articleNO);
		modelAndView.addObject("article", article);
		boolean isAuthor = article.getId().equals(member.getEmail());
		if(!isAuthor)
			modelAndView.setViewName("redirect:/mainboard/1");
		return modelAndView;
	}
	
}
