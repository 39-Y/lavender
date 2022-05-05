package com.spring.LAB.board.domain.articles;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.spring.LAB.board.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ArticlesTable")



public class Articles extends BaseTimeEntity{
	
	@SequenceGenerator(
    name="article_Seq", 
    sequenceName="art_seq",
    initialValue=1,
    allocationSize = 1
    )
	@Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "article_Seq")
	private Long articleNO;
	
	@Column()
	private Long parentNO;
	
	@Column()
	private Long hits;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String id;
	
	@Builder
	public Articles(String title,
									String content,
									String id,
									Long parentNO) 
	{
		this.title = title;
		this.content = content;
		this.id = id;
		this.parentNO = parentNO;
	}
	
}
