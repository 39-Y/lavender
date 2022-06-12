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
import com.spring.LAB.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ArticleIndexController {
	final ArticlesJpaService jpaService;
	final ArticleService articleService;
	final HttpSession session;
	
	@GetMapping("/imgtest") 
	public String imgtest() {
		return "/board/imgtest";
	}
	
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
			System.out.println("--------EndNO: " + pageLinkIdx.articleEndNum);
			articleService.findArticlePage(pageLinkIdx);
			System.out.println("--------EndNO: " + pageLinkIdx.articleEndNum);
			System.out.println("--------list: " + pageLinkIdx.articlesList.size());
			modelAndView.addObject("pageLinkIdx", pageLinkIdx);
			return modelAndView;
	}
	
		@GetMapping(value="/article/{articleNO}")
	public ModelAndView articlePage(@PathVariable long articleNO,HttpServletRequest request) {
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		imgFileListSession.deleteList();
		ModelAndView modelAndView = new ModelAndView("/board/articlePage");
		ArticleListResponseDTO article = articleService.findByArticleNO(articleNO);
		modelAndView.addObject("article", article);
		return modelAndView;
	}
		
	@GetMapping(value="/articles/write")
	public ModelAndView articlesWrite(@RequestParam(required = false) String parentNO,
																		HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView("board/writePage");
			ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
			imgFileListSession.initList();
			modelAndView.addObject("parentNO", parentNO);
			/*HttpSession session = request.getSession();
			if(session.getAttribute("memberVO") == null) {
				modelAndView.setViewName("redirect:/lavender/nidlogin");
				return modelAndView;
			}*/
			return modelAndView;
	}
	
	@GetMapping(value="/articles/update/{articleNO}")
	public ModelAndView modArticlePage(@PathVariable(value="articleNO")long articleNO) {
		ModelAndView modelAndView = new ModelAndView("board/updatePage");
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		imgFileListSession.initList();
		ArticleListResponseDTO article = articleService.findByArticleNO(articleNO);
		modelAndView.addObject("article", article);
			/*MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			//ArticleVO articleVO = articleService.viewArticle(articleNO);
			String loginId = memberVO.getId();
			//String writerId = articleVO.getId();
			//if(loginId.equals(writerId)) {
				modelAndView.setViewName("board/modPage");
				//modelAndView.addObject("articleVO",articleVO);
			//}
			//else 
				modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
			modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		*/
		return modelAndView;
	}
	
}
