package com.spring.LAB.board.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class ArticleVO {
	private String id;
	private String title;
	private String content;
	private long articleNO;
	private long parentNO = 0;
	private int level;
	private long hits;
	private Date writeDate;
	private String dirName;
}
