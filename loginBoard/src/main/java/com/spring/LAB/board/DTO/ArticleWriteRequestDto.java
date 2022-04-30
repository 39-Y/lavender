package com.spring.LAB.board.DTO;

import com.spring.LAB.board.domain.articles.Articles;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class ArticleWriteRequestDto {
	private String title;
	private String content;
	private String id;
	private Long parentNO;
	
	@Builder
	public ArticleWriteRequestDto(String title, 
																String content, 
																String id, 
																Long parnetNO) {
		this.title = title;
		this.content = content;
		this.id = id;
		this.parentNO = parnetNO;
	}
	
	public Articles toEntity() {
		return Articles.builder()
									.title(title)
									.content(content)
									.id(id)
									.parentNO(parentNO)
									.build();
	}
}
