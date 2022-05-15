package com.spring.LAB.board.DTO.article;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
	@Getter
	@NoArgsConstructor
	public class ArticleUpdateRequestDTO {
		private String title;
		private String content;
		private Long articleNO;
		
		@Builder
		public ArticleUpdateRequestDTO(String title, 
																	String content, 
																	Long articleNO) {
			this.title = title;
			this.content = content;
			this.articleNO = articleNO;
		}
}
