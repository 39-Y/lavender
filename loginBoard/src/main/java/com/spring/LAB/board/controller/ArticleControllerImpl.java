package com.spring.LAB.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.DTO.ArticleWriteRequestDto;
import com.spring.LAB.board.DTO.ImgFileListResponseDTO;
import com.spring.LAB.board.DTO.ImgFileRequestDTO;
import com.spring.LAB.board.domain.PageLinkIndex;
import com.spring.LAB.board.domain.imgUpload.RequestImgFileImplement;
import com.spring.LAB.board.service.ArticleService;
import com.spring.LAB.board.service.ArticlesJpaService;
import com.spring.LAB.board.vo.ArticleVO;
import com.spring.LAB.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("articleController")
public class ArticleControllerImpl implements ArticleController{
	final ArticleService articleService; //myBatis
	final ArticlesJpaService articlesService; //jpa 
	private HttpSession session;
	
	@PostMapping(value="/imgtest")
	public ResponseEntity imgTest(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(file.getOriginalFilename());
		System.out.println("image/png");
		System.out.println(file.getBytes().length);
		HttpHeaders h = new HttpHeaders();
		h.add("Content-Type", "image/png");
		h.add("Content-Length", String.valueOf(file.getBytes().length));
		/*HttpHeaders h = new HttpHeaders();
		h.add("Content-Type", "image/png");
		h.add("Content-Length", String.valueOf(fileByte.length));
		return new ResponseEntity<byte[]>(file.getBytes(), h, HttpStatus.OK);
		*/
		return new ResponseEntity<byte[]>(file.getBytes(), h, HttpStatus.OK);
	}
	
	@GetMapping(value="/img/preview/{fileName}")
	public ResponseEntity imgPreview(HttpServletRequest req, 
								@PathVariable("fileName") String fileName) throws IOException {
		HttpHeaders header = new HttpHeaders();
		session= req.getSession();
		List<ImgFileRequestDTO> imgFilesList = 
								(List<ImgFileRequestDTO>) session.getAttribute("imgFilesList");
		for(ImgFileRequestDTO imgFile : imgFilesList) {
			if(fileName.equals(imgFile.getFileName())) {
				byte[] fileByte = imgFile.getFileByte();
				header.add("Content-Type", imgFile.getMimeType());
				header.add("Content-Length", String.valueOf(fileByte.length));
				return new ResponseEntity<byte[]>(fileByte, header, HttpStatus.OK);
			}
		}
		return null;
	}
	
	@GetMapping(value="/test")
	public String tete() {
		String hi = "hi yo";
		return hi;
	}
	// rest로 표현
	@PostMapping(value="/articles/write")
	public Long save(@RequestBody ArticleWriteRequestDto articleDto) {
		return articlesService.save(articleDto);
	}
	
	@Override
	@GetMapping(value="/lavender/article")
	public ModelAndView viewArticle(@RequestParam(value="articleNO")long articleNO) {
		articleService.upHits(articleNO);
		ModelAndView modelAndView = new ModelAndView("board/articlePage");
		ArticleVO articleVO = articleService.viewArticle(articleNO);
		modelAndView.addObject("articleVO",articleVO);
		return modelAndView;
	}
	/*
		by 양혜정 (2022.03.27)
		html에서 전달된 게시글 정보를 Service로 전달
	*/
	@Override
	@PostMapping(value="/lavender/writeboard")
	public ModelAndView writeArticle(HttpServletRequest request, ArticleWriteRequestDto article) {
		String content = article.getContent();
		session = request.getSession();
		List<ImgFileRequestDTO> imgFilesList 
							= (List<ImgFileRequestDTO>) session.getAttribute("imgFilesList");
		if(imgFilesList !=null) {
			//content내용을 변경 preview를 view로 대체
			
			//content와 session의 img 유무 검토
			for(ImgFileRequestDTO imgFile: imgFilesList) {
				String fileName = imgFile.getFileName();
				if(0<content.indexOf(fileName)) {
					//넣을 것들 index를 int로 저장
				}
			}
			
			//imgDTO를 service로 저장
		}
		//articleDTO service로 저장
		session.removeAttribute("dirName");
		//long articleNO = articleService.addArticle(articleVO);
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/article?articleNO=");
		return modelAndView;
	}
//by 양혜정, 클라이언트가 글쓰기를 요청할시 로그인 확인 후 view return
//(2022.03.27)
	@GetMapping(value="/lavender/writeboard")
	public ModelAndView writeArticlePage(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("board/writePage");
			/*HttpSession session = request.getSession();
			if(session.getAttribute("memberVO") == null) {
				modelAndView.setViewName("redirect:/lavender/nidlogin");
				return modelAndView;
			}
			if(session.getAttribute("dirName") != null)
				session.removeAttribute("dirName");
			if(request.getParameter("parentNO") != null) {
				long parentNO = Long.parseLong(request.getParameter("parentNO"));
				System.out.println("parentNO: "+parentNO);
				modelAndView.addObject("parentNO", parentNO);
			}	*/
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView("redirect:/lavender/nidlogin");
			return modelAndView;
		}
		
	}

	@Override
	@PostMapping(value="/lavender/modboard")
	public ModelAndView modArticle(HttpServletRequest request, ArticleVO articleVO) {
		HttpSession session = request.getSession();
		if(session.getAttribute("dirName") !=null) { 
			String dirName = (String) session.getAttribute("dirName");
			//moveImgFile(dirName);
			String absolPath ="c:/img/article/";
			boolean onlyText = true;
					//imgFileCheckAndDelete(absolPath, dirName, articleVO.getContent());
			System.out.println("onlyText: "+onlyText);
			if(!onlyText) {
				String imgChangeContent = articleVO.getContent().replace("temp/"+dirName, dirName);
				articleVO.setContent(imgChangeContent);
				articleVO.setDirName(dirName);
			}
			session.removeAttribute("dirName");
			session.removeAttribute("articleMod");
		}
		long articleNO = articleVO.getArticleNO();
		articleService.modArticle(articleVO);
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/article?articleNO="+articleNO);
		return modelAndView;
	}

	@GetMapping(value="/lavender/modboard")
	public ModelAndView modArticlePage(HttpServletRequest request, 
			@RequestParam(value="articleNO")long articleNO) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		try {
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			ArticleVO articleVO = articleService.viewArticle(articleNO);
			String loginId = memberVO.getId();
			String writerId = articleVO.getId();
			if(loginId.equals(writerId)) {
				if(articleVO.getDirName() !=null) {
					session.setAttribute("dirName", articleVO.getDirName());
					session.setAttribute("articleMod", "articleMod");
				}
				modelAndView.setViewName("board/modPage");
				modelAndView.addObject("articleVO",articleVO);
			}
			else 
				modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		}

		return modelAndView;
	}

	@Override
	@GetMapping("/lavender/deleteboard")
	public ModelAndView deleteArticle(HttpServletRequest request,
			@RequestParam(value="articleNO") long articleNO) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		ArticleVO articleVO = articleService.viewArticle(articleNO);
		try {
			String loginId = memberVO.getId();
			String writerId = articleVO.getId();
			if(loginId.equals(writerId) || loginId.equals("admin")) {
				articleService.deleteArticle(articleNO);
				if(articleVO.getDirName() !=null)
					//deleteImgFile("c:/img/article/",articleVO.getDirName());
				modelAndView.setViewName("redirect:/lavender/mainboard");
			}
			else modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		}

		return modelAndView;
	}

	@PostMapping(value="/articles/write/imgupload")
	public ResponseEntity<String> imgUpload(HttpServletRequest req) throws IOException {
		RequestImgFileImplement imgImplement = new RequestImgFileImplement(req);
		imgImplement.loadToSession();
		String fileName = imgImplement.getFileName();
		String fileInfo = "&bNewLine=true&sFileName=" + fileName
											+ "&sFileURL=" + "/img/preview/" + fileName;
		return new ResponseEntity<String> (fileInfo, null, HttpStatus.OK);
	}

}
