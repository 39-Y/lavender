package com.spring.LAB.board.DTO.article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ArticleListResponseDTO {
	private String title;
	private String content;
	private String id;
	private int level;
	private int hits;
	private long articleNO;
	private LocalDateTime modifiedDate;
	private String parsedModifiedDate;
	private String minParsedModifiedDate;
	
	public ArticleListResponseDTO(long articleNO,
																String title, 
																String content, 
																String id,
																int level,
																int hits,
																LocalDateTime modifiedDate ) {
		this.articleNO = articleNO;
		this.title = title;
		this.content = content;
		this.id = id;
		this.level = level;
		this.hits = hits;
		this.modifiedDate = modifiedDate;
		
	}
	public void parseModifiedDate() {
		parsedModifiedDate = modifiedDate.format
														(DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss"));
		minParsedModifiedDate = modifiedDate.format
				(DateTimeFormatter.ofPattern("yyyy.MM.dd."));
	}
}