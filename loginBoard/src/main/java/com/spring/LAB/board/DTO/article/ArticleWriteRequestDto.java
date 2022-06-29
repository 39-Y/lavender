package com.spring.LAB.board.DTO.article;

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
	private String picture;
	@Builder
	public ArticleWriteRequestDto(String title, 
																String content, 
																String id,
																String picture,
																Long parnetNO) {
		this.title = title;
		this.content = content;
		this.id = id;
		this.parentNO = parnetNO;
		this.picture = picture;
	}
	
	public Articles toEntity() {
		return Articles.builder()
									.title(title)
									.content(content)
									.picture(picture)
									.id(id)
									.parentNO(parentNO)
									.build();
	}
}
