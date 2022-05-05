package com.spring.LAB.board.domain.imgUpload;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.LAB.board.domain.articles.Articles;

public interface ImgFileRepository extends JpaRepository<ImgFiles, Long>{

}
