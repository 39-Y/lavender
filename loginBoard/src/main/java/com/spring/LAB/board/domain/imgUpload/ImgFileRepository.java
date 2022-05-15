package com.spring.LAB.board.domain.imgUpload;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImgFileRepository extends JpaRepository<ImgFiles, Long>{
	@Query(value="SELECT img FROM ImgFiles img WHERE articleNO = :articleNO")
	public List<ImgFiles> findAllByArticleNO(@Param("articleNO") Long articleNO);
}
