package com.spring.LAB.board.domain.imgUpload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "imgFilesTable")

public class ImgFiles {
	@SequenceGenerator(
	    name="imgFile_Seq", 
	    sequenceName="img_seq",
	    initialValue=1,
	    allocationSize = 1
	 )
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "imgFile_Seq")
	private Long imgNO;
	
	@Column()
	private Long articleNO;
	
	@Column()
	private String originalName;
	
	@Column()
	private String fileName;
	
	@Column()
	private String mimeType;
	
	@Column()
	private byte[] fileByte;
	
	@Builder
	public ImgFiles(long articleNO,
									String originalName,
									String fileName,
									String mimeType,
									byte[] fileByte) {
		this.articleNO = articleNO;
		this.originalName = originalName;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileByte = fileByte;
	}
}
