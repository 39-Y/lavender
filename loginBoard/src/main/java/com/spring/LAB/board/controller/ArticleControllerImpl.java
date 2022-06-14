package com.spring.LAB.board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.LAB.board.DTO.article.ArticleUpdateRequestDTO;
import com.spring.LAB.board.DTO.article.ArticleWriteRequestDto;
import com.spring.LAB.board.DTO.imgFile.ImgFileDTO;
import com.spring.LAB.board.DTO.imgFile.ImgFileRequestDTO;
import com.spring.LAB.board.domain.imgUpload.ImgFilesListSession;
import com.spring.LAB.board.domain.imgUpload.RequestImgFileImplement;
import com.spring.LAB.board.domain.imgUpload.imgFileLoadHeader;
import com.spring.LAB.board.service.ArticleService;
import com.spring.LAB.board.service.ArticlesJpaService;
import com.spring.LAB.board.service.imgFile.ImgFileJpaService;
import com.spring.LAB.board.service.imgFile.ImgFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("articleController")
public class ArticleControllerImpl implements ArticleController{
	final ArticleService articleService; //myBatis
	final ArticlesJpaService articleJpaService; //jpa 
	final ImgFileJpaService imgFileJpaService;
	final ImgFileService imgFileService;
	final HttpSession session;

	@Override
	@PostMapping(value="/articles/write")
	public long writeArticle(@RequestBody ArticleWriteRequestDto article) {
		long articleNO = articleJpaService.save(article);
			ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
			List<ImgFileRequestDTO> imgFilesList 
							= imgFileListSession.findImgFiles(article.getContent(), articleNO);
			if(imgFilesList !=null) 
				imgFileJpaService.saveAll(imgFilesList);
			imgFileListSession.deleteList();
		return articleNO;
	}
	
	@PostMapping(value="/articles/write/imgupload")
	public ResponseEntity<String> imgUpload(HttpServletRequest request) throws IOException, InterruptedException {
		RequestImgFileImplement imgImplement = new RequestImgFileImplement(request);
		imgImplement.imgLoadToSession();
		String fileName = imgImplement.getFileName();
		String fileInfo = "&bNewLine=true&sFileName=" + fileName
				+ "&sFileURL=" + "/article/img/" + fileName;
		return new ResponseEntity<String> (fileInfo, null, HttpStatus.OK);
	}
	
	@GetMapping(value="/article/img/{fileName}")
	public ResponseEntity<byte[]> viewImgFile(@PathVariable("fileName") String fileName) 
																										throws IOException, SQLException {
		imgFileLoadHeader imgFileHeader = new imgFileLoadHeader();
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		List<ImgFileRequestDTO> imgFilesList = imgFileListSession.findImgFiles(fileName);
		ImgFileDTO imgDTO = null;
		if(imgFilesList != null) 
			imgDTO = imgFilesList.get(0);
		else 
			 imgDTO = imgFileService.findByFileName(fileName);
		return imgFileHeader.load(imgDTO);
	}

	@Override
	@PutMapping(value="/articles/update")
	public long updateArticle(@RequestBody ArticleUpdateRequestDTO article) {
		long articleNO = articleJpaService.update(article);
		if(imgFileService.countImgFiles(articleNO) > 0)
			imgFileJpaService.deleteNotExistImgFiles(article);
		ImgFilesListSession imgFileListSession = new ImgFilesListSession(session);
		List<ImgFileRequestDTO> imgFilesList 
						= imgFileListSession.findImgFiles(article.getContent(), articleNO);
		if(imgFilesList !=null) 
			imgFileJpaService.saveAll(imgFilesList);
		imgFileListSession.deleteList();
		return articleNO;
	}

	@Override
	@DeleteMapping("/articles/delete")
	public void deleteArticle(@RequestBody long articleNO) {
		articleJpaService.delete(articleNO);
	}
}
