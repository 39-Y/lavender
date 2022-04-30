package com.spring.LAB.board.domain.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Articles, Long>{
	
}
