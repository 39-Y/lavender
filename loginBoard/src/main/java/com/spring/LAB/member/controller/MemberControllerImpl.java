package com.spring.LAB.member.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.spring.LAB.member.service.MemberService;
import com.spring.LAB.member.vo.MemberVO;

@RestController("memberController")
public class MemberControllerImpl implements MemberController{
	@Autowired 
	private MemberService memberService;

	MemberVO memberVO = new MemberVO();
	@Override
	@RequestMapping(value="/lavender/main.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		List<MemberVO> memberList = memberService.listMember();
		ModelAndView modelAndView = new ModelAndView("main");
		modelAndView.addObject("memberList",memberList);
		return modelAndView;
	}

	@Override
	@PostMapping(value="/lavender/nidlogin")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		String id = (String)request.getParameter("userId");
		String pw = (String)request.getParameter("userPw");
		String view="login/loginPage";
		ModelAndView modelAndView = new ModelAndView();
		if(id!=null) {
			boolean logOn = memberService.isMember(id, pw);
			if(logOn) {
				memberVO = memberService.VOMember(id);
				HttpSession session = request.getSession();
				session.setAttribute("memberVO", memberVO);
				if(memberVO.getProfileImg() != null) {
					String imgUrl = "/img/profile/"+memberVO.getId()+"/"+memberVO.getProfileImg();
					System.out.println(imgUrl);
					session.setAttribute("imgUrl", imgUrl);
				}
				else {
					System.out.println("profile null");
					session.setAttribute("imgUrl", null);
				}
				view="redirect:/lavender/main.do";
				}
			else
				modelAndView.addObject("notMatch", true);
		}
		modelAndView.setViewName(view);
		return modelAndView;
	}
	@GetMapping(value="/lavender/nidlogin")
	public ModelAndView logInPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("memberVO")!=null)
			modelAndView.setViewName("redirect:/lavender/main.do");
		else
			modelAndView.setViewName("login/loginPage");
		return modelAndView;
	}
	
	@Override
	@PostMapping(value="/lavender/register")
	public ModelAndView register(HttpServletRequest request, MemberVO memberVO) throws Exception {
		memberService.addMember(memberVO);
		HttpSession session = request.getSession();
		session.setAttribute("memberVO", memberVO);
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/profile?register=new");
		return modelAndView;
	}
	@GetMapping(value="/lavender/register")
	public ModelAndView registerPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("login/register");
		return modelAndView;
	}
	@ResponseBody
	@PostMapping(value="/lavender/register/check")
	public String isOverlapped(String id) throws Exception {
		String result = memberService.isOverlapped(id);
		return result;
	}
	
	@PostMapping(value="/lavender/profile")
	public ModelAndView setProfileImg(@RequestParam(value="profileImg") MultipartFile profileFile,
																		@RequestParam(value="id") String id,
																		HttpServletRequest request)
	 throws Exception {
		HttpSession session = request.getSession();
		String originalFilename = profileFile.getOriginalFilename();
		int dot = originalFilename.indexOf(".");
		String extension = originalFilename.substring(dot);
		String profileImg = id+"profile"+extension;
		
		String absolPath = "c:/img/profile/";
		File fileDir = new File(absolPath+id); 
		if (!fileDir.exists()) { fileDir.mkdirs(); }
		File imgFile = new File(absolPath+id, profileImg);
		if(imgFile.exists()) imgFile.delete();
		profileFile.transferTo(imgFile);
		
		memberService.profileMember(profileImg, id);
		memberVO = memberService.VOMember(id);
		session.setAttribute("memberVO", memberVO);
		String imgUrl = "/img/profile/"+memberVO.getId()+"/"+memberVO.getProfileImg();
		System.out.println("profile-"+imgUrl);
		session.setAttribute("imgUrl", imgUrl);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/main.do");
		return modelAndView;
	}
	
	@GetMapping(value="/lavender/profile")
		public ModelAndView profilePage(HttpServletRequest request) {
			HttpSession session = request.getSession();
			ModelAndView modelAndView = new ModelAndView("redirect:/lavender/nidlogin");
			if(session.getAttribute("memberVO") != null) 
				modelAndView.setViewName("login/profilePage");
			return modelAndView;
		}
	
	
	@Override
	@PostMapping(value="/lavender/modmember")
	public ModelAndView modMember(HttpServletRequest request, MemberVO memberVO) throws Exception {
		HttpSession session = request.getSession();
		MemberVO _memberVO = (MemberVO)session.getAttribute("memberVO");
		String pw = memberVO.getPw();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		System.out.println("name: "+memberVO.getName());
		if(pw=="") 
			memberVO.setPw(_memberVO.getPw());
		if(name.length()==0) 
			memberVO.setName(_memberVO.getName());
		if(email=="")
			memberVO.setEmail(_memberVO.getEmail());
		
		memberService.modMember(memberVO);
		session.removeAttribute("memberVO");
		session.setAttribute("memberVO", memberVO);
		ModelAndView modelAndView = new ModelAndView("login/modPage");
		modelAndView.addObject("showModInfo",1);
		return modelAndView;
	}
	
	@GetMapping(value="/lavender/modmember")
	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("memberVO")!=null)
			modelAndView.setViewName("login/modPage");
		else
			modelAndView.setViewName("redirect:/lavender/nidlogin");
		return modelAndView;
	}
	@ResponseBody
	@PostMapping(value="/lavender/modmember/check")
	public boolean isRightPw(HttpServletRequest request, String id, String pw) throws Exception {
		boolean result = memberService.isMember(id, pw);
		return result;
	}
	
	@GetMapping(value="/lavender/logout")
		public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView modelAndview = new ModelAndView("redirect:/lavender/main.do");
		return modelAndview;
	}

	@Override
	@GetMapping(value="/lavender/deletemember")
	public ModelAndView deleteMember(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String id=request.getParameter("id");
		System.out.println("id: "+id);
		String absolPath = "c:/img/profile/";
		File imgPath = new File(absolPath+id);
			while(imgPath.exists()) {
				File[] imgFiles = imgPath.listFiles();
				if(imgFiles !=null) {
					for(File file : imgFiles) {
						file.delete();
						System.out.println(file+"삭제");
					}
				}
				System.gc();
				System.runFinalization();
				imgPath.delete();
		}
		memberService.deleteMember(id);
		session.invalidate();
		ModelAndView modelAndview = new ModelAndView("redirect:/lavender/nidlogin");
		return modelAndview;
	}
	@Override
	@RequestMapping(value="/lavender/admin", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView admin(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView("redirect:/lavender/main.do");
		
		if(session.getAttribute("memberVO") != null) {
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			if("admin".equals(memberVO.getId())){
				List<MemberVO> memberList = memberService.listMember();
				modelAndView.setViewName("login/admin");
				modelAndView.addObject("memberList",memberList);
			}
		}
		
		return modelAndView;
	}
}
