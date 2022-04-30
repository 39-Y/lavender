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
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.LAB.board.DTO.ArticleWriteRequestDto;
import com.spring.LAB.board.domain.PageLinkIndex;
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

	@Override
	@GetMapping(value="/lavender/mainboard1")
	public ModelAndView mainBoardPage(HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView("/board/mainboard");
			/*int articlesTotal = articleService.countAllArticle();
			if(articlesTotal == 0) {
				return modelAndView;
			}
			PageLinkIndex pageLinkIdx = new PageLinkIndex(articlesTotal);
			
			//modelAndView.addObject("pageLinkIdxMap", pageLinkIdxMap);*/
			return modelAndView;
	}
	// rest로 표현
	@PostMapping(value="/lavender/write")
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
	public ModelAndView writeArticle(HttpServletRequest request, ArticleVO articleVO) {
		HttpSession session = request.getSession();
		if(session.getAttribute("dirName") !=null) {
			String dirName = (String) session.getAttribute("dirName");
			String tempAbsolPath ="c:/img/article/temp/";
			boolean onlyText = imgFileCheckAndDelete(tempAbsolPath, dirName, articleVO.getContent());
			System.out.println("onlyText: "+onlyText);
			if(!onlyText) {
				String imgChangeContent = articleVO.getContent().replace("temp/"+dirName, dirName);
				articleVO.setContent(imgChangeContent);
				moveImgDir(dirName);
				articleVO.setDirName(dirName);
			}
			session.removeAttribute("dirName");
		}
		long articleNO = articleService.addArticle(articleVO);
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/article?articleNO="+articleNO);
		return modelAndView;
	}
//by 양혜정, 클라이언트가 글쓰기를 요청할시 로그인 확인 후 view return
//(2022.03.27)
	@GetMapping(value="/lavender/writeboard")
	public ModelAndView writeArticlePage(HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("board/writePage");
			HttpSession session = request.getSession();
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
			}	
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
			moveImgFile(dirName);
			String absolPath ="c:/img/article/";
			boolean onlyText = imgFileCheckAndDelete(absolPath, dirName, articleVO.getContent());
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
					deleteImgFile("c:/img/article/",articleVO.getDirName());
				modelAndView.setViewName("redirect:/lavender/mainboard");
			}
			else modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:/lavender/article?articleNO="+articleNO);
		}

		return modelAndView;
	}

	@RequestMapping(value="/lavender/articleboard/imgupload", method= {RequestMethod.GET, RequestMethod.POST})
	public void imgUpload(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/x-www-form-urlencoded; charset=utf-8");
		PrintWriter out =  res.getWriter();
		HttpSession session = req.getSession();
		String dirName;
		SimpleDateFormat imgDateFormat = new SimpleDateFormat("SSS");
		if(session.getAttribute("dirName") != null) 
			dirName = (String) session.getAttribute("dirName");
		else {
			SimpleDateFormat dirDateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
			dirName = dirDateFormat.format(new Date());
			session.setAttribute("dirName", dirName);
		}
		String orgImgFileName = req.getHeader("file-name");
		String imgFileName = imgDateFormat.format(new Date())+orgImgFileName;
		//imgFileName = URLDecoder.decode(imgFileName);
		String tempAbsolPath="/img/article/temp";
		String filePath = mkDirs("c:"+tempAbsolPath, dirName);
		imgStreaming(req, filePath, imgFileName);
		String fileInfo = "&bNewLine=true&sFileName=" + imgFileName;
		fileInfo += "&sFileURL=" + tempAbsolPath + "/" + dirName + "/" + imgFileName;
		out.println(fileInfo);
	}


	@GetMapping(value="/lavender/test")
	public String test() {
		String hi = "hi";

		return hi;
	}

	private String mkDirs(String absolPath, String dirName) {
		String filePath = absolPath+ "/" + dirName + "/";
		File file = new File(filePath);
		if(!file.exists()) 
			file. mkdirs();
		System.out.println(file.getAbsolutePath());
		return filePath;
	}

	private void imgStreaming(HttpServletRequest req, String filePath, String imgFileName) 
			throws IOException {
		InputStream is = req.getInputStream();
		OutputStream os = new FileOutputStream(filePath+imgFileName);
		int fileSize = Integer.parseInt(req.getHeader("file-size"));
		byte b[] = new byte[fileSize];
		int i;
		while((i=is.read(b,0,b.length))!=-1) {
			os.write(b,0,i);
		}
		is.close();
		os.flush();
		os.close();
	}

	private boolean imgFileCheckAndDelete(String absolPath, String dirName, String articleContent) {
		File imgDir = new File(absolPath+dirName);
		String[] imgFiles = imgDir.list();
		for(int i=0; i<imgFiles.length; i++) {
			System.out.println("findImgFile: "+imgFiles[i]);
			if(!articleContent.contains(dirName+"/"+imgFiles[i])) {
				System.out.println("deleteFile: "+imgFiles[i]);
				deleteImgFile(absolPath,dirName+"/"+imgFiles[i]);
			} 
		}
		if(imgDir.list().length == 0) 
			return true;
		else 
			return false;
	}

	private void deleteImgFile(String absolPath, String fileName) {
		String filePath = absolPath+fileName;
		System.out.println("deletePath: "+filePath);
		File imgDir = new File(filePath);
		while(imgDir.exists()) {
			File[] imgFiles = imgDir.listFiles();
			if(imgFiles !=null) {
				for(File file : imgFiles) {
					file.delete();
					System.out.println(file+"삭제");
				}
			}
			System.gc();
			System.runFinalization();
			imgDir.delete();
		}
	}

	private void moveImgDir(String dirName) {
		String absolPath = "c:/img/article/";
		Path file = Paths.get(absolPath+"temp/"+dirName);
		Path fileToMove = Paths.get(absolPath+dirName);
		try {
			Files.move(file, fileToMove);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void moveImgFile(String dirName) {
		String absolPath = "c:/img/article/";
		File imgDir = new File(absolPath+"temp/"+dirName);
		String[] imgFiles = imgDir.list();
		if(imgFiles !=null) {
			for(String fileName: imgFiles) {
				Path file = Paths.get(absolPath+"temp/"+dirName+"/"+fileName);
				Path fileToMove = Paths.get(absolPath+dirName+"/"+fileName);
				try {
					Files.move(file, fileToMove);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
